package com.jorgegatica.snomedbrowser;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgegatica.snomedbrowser.model.Concept;
import com.jorgegatica.snomedbrowser.model.Description;
import com.jorgegatica.snomedbrowser.model.Parent;
import com.jorgegatica.snomedbrowser.model.Relationship;
import com.jorgegatica.snomedbrowser.service.SnomedAPI;
import com.jorgegatica.snomedbrowser.util.ApiError;
import com.jorgegatica.snomedbrowser.util.ErrorUtils;
import com.jorgegatica.snomedbrowser.util.RecyclerViewEmptySupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jorgegatica.snomedbrowser.service.SnomedAPI.EDITION;
import static com.jorgegatica.snomedbrowser.service.SnomedAPI.RELEASE;

public class ConceptActivity extends AppCompatActivity {


    @BindView(R.id.rv_parents)
    RecyclerViewEmptySupport rvParents;
    @BindView(R.id.cv_parents)
    CardView cvParents;
    @BindView(R.id.tv_sctid)
    TextView tvSctid;
    @BindView(R.id.rv_sinonimos)
    RecyclerView rvSinonimos;
    @BindView(R.id.cv_concept)
    CardView cvConcept;
    @BindView(R.id.rv_finding_site)
    RecyclerView rvFindingSite;
    @BindView(R.id.cv_finding_site)
    CardView cvFindingSite;
    @BindView(R.id.tv_children)
    TextView tvChildren;
    @BindView(R.id.rv_children)
    RecyclerViewEmptySupport rvChildren;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;


    SnomedAdapter parentsAdapter;
    SnomedAdapter childrenAdapter;
    SnomedAdapter descriptionsAdapter;
    SnomedAdapter findingSitesAdapter;

    String conceptId;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept);

        ButterKnife.bind(this);

        rvChildren.setEmptyView(findViewById(R.id.empty_children));

        rvParents.setEmptyView(findViewById(R.id.empty_parents));

        collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.DEFAULT_BOLD);

        Bundle bundle = getIntent().getExtras();
        conceptId = bundle.getString("conceptId");
        SnomedAPI.createDescriptionService().getConceptById(conceptId).enqueue(new Callback<Concept>() {
            @Override
            public void onResponse(Call<Concept> call, Response<Concept> response) {
                if (response.isSuccessful()) {
                    Concept c = response.body();

                    System.out.println(conceptId + " | " + c.getFsn() + " |");
                    tvSctid.setText(conceptId + " | " + c.getFsn() + " |");
                    tvSctid.setOnClickListener(v -> {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("concept", conceptId + " | " + c.getFsn() + " |");
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(ConceptActivity.this, "Copied to Clipboard: "+conceptId + " | " + c.getFsn() + " |", Toast.LENGTH_SHORT).show();
                    });

                    descriptionsAdapter = new SnomedAdapter(getApplicationContext(), getParent());

                    if (c.getDescriptions() != null) {
                        for (Description d : c.getDescriptions()) {
                            if (d.isActive()) {
                                descriptionsAdapter.add(d);
                            }
                        }
                    }

                    rvSinonimos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvSinonimos.setAdapter(descriptionsAdapter);
                    rvSinonimos.setNestedScrollingEnabled(false);


                    findingSitesAdapter = new SnomedAdapter(getApplicationContext(), getParent());
                    if (c.getStatedRelationships() != null) {
                        for (Relationship r : c.getStatedRelationships()) {
                            System.out.println("Inside foreach rel");
                            if (r.isActive()) {
                                findingSitesAdapter.add(r);
                            }
                        }
                    }
                    rvFindingSite.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvFindingSite.setAdapter(findingSitesAdapter);
                    rvFindingSite.setNestedScrollingEnabled(false);


                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getApplicationContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Concept> call, Throwable t) {
                Toast.makeText(ConceptActivity.this, "ON FAILURE!", Toast.LENGTH_SHORT).show();
            }
        });

        childrenAdapter = new SnomedAdapter(getApplicationContext(), getParent());
        rvChildren.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvChildren.setAdapter(childrenAdapter);

        SnomedAPI.createDescriptionService().findAllChildrenOfConcept(EDITION, RELEASE, conceptId, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(c -> childrenAdapter.addAll(c));

        SnomedAPI.createDescriptionService().findAllParentsOfConcept(EDITION, RELEASE, conceptId, null).enqueue(new Callback<List<Parent>>() {
            @Override
            public void onResponse(Call<List<Parent>> call, Response<List<Parent>> response) {
                if (response.isSuccessful()) {
                    System.out.println("!SIZE: " + response.body().size());
                    parentsAdapter = new SnomedAdapter(getApplicationContext(), getParent());
                    parentsAdapter.addAll(response.body());
                    rvParents.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvParents.setAdapter(parentsAdapter);
                } else {
                    System.out.println("NOT RECOGNIZED!!");
                }
            }

            @Override
            public void onFailure(Call<List<Parent>> call, Throwable t) {
                System.out.println("ON FAILURE!!!");
            }
        });


    }
}
