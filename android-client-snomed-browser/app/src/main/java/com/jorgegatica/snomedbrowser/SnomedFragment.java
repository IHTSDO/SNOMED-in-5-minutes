package com.jorgegatica.snomedbrowser;


import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgegatica.snomedbrowser.model.Concept;
import com.jorgegatica.snomedbrowser.model.MatchResults;
import com.jorgegatica.snomedbrowser.service.SnomedAPI;
import com.jorgegatica.snomedbrowser.util.RecyclerViewEmptySupport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jorgegatica.snomedbrowser.service.SnomedAPI.EDITION;
import static com.jorgegatica.snomedbrowser.service.SnomedAPI.RELEASE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SnomedFragment extends Fragment implements SearchView.OnQueryTextListener {


    private static final int BY_CONCEPT_ID = 1;
    private static final int BY_DESCRIPTION_ID = 2;
    private static final int BY_TERM = 3;
    @BindView(R.id.rv_json_response)
    RecyclerViewEmptySupport rvJsonResponse;
    @BindView(R.id.search_type)
    LinearLayout btnSearchType;
    @BindView(R.id.tv_search_type)
    TextView tvSearchType;
    @BindView(R.id.tv_search_bar)
    SearchView tvSearchBar;

    Unbinder unbinder;

    SnomedAdapter adapter;
    int searchType = BY_TERM;

    public SnomedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snomed, container, false);
        unbinder = ButterKnife.bind(this, view);

        adapter = new SnomedAdapter(getContext(), getActivity());
        rvJsonResponse.setEmptyView(view.findViewById(R.id.empty_rv_view));

        tvSearchBar.setQueryHint("Type at least 3 characters  Example: shou fra");
        tvSearchBar.setOnClickListener(v -> {
            tvSearchBar.setFocusable(true);
            tvSearchBar.setIconified(false);
            tvSearchBar.requestFocusFromTouch();
        });

        tvSearchBar.setOnQueryTextListener(this);

        rvJsonResponse.setLayoutManager(new LinearLayoutManager(getContext()));
        rvJsonResponse.setAdapter(adapter);
        rvJsonResponse.setNestedScrollingEnabled(false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void search() {

        switch (searchType) {
            case BY_CONCEPT_ID:

                SnomedAPI.createDescriptionService()
                        .getConceptById(tvSearchBar.getQuery().toString())
                        .enqueue(new Callback<Concept>() {
                            @Override
                            public void onResponse(Call<Concept> call, Response<Concept> response) {

                                if (response.isSuccessful()) {
                                    adapter.clear();
                                    adapter.add(response.body());

                                } else {
                                    Toast.makeText(getContext(), response.raw().toString(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Concept> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case BY_DESCRIPTION_ID:
                SnomedAPI.createDescriptionService()
                        .getDescriptionById(tvSearchBar.getQuery().toString())
                        .enqueue(new Callback<MatchResults>() {
                            @Override
                            public void onResponse(Call<MatchResults> call, Response<MatchResults> response) {
                                if (response.body().getDetails().size() > 0) {
                                    if (response.isSuccessful()) {
                                        try {
                                            adapter.clear();
                                            adapter.addAll(response.body().getMatches());
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    } else {
                                        Toast.makeText(getContext(), response.raw().toString(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<MatchResults> call, Throwable t) {
                                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println(t.getMessage());
                                System.out.println(t.getLocalizedMessage());
                            }
                        });

                break;
            case BY_TERM:
                SnomedAPI.createDescriptionService().searchDescriptions(EDITION,
                        RELEASE, tvSearchBar.getQuery().toString(),
                        "partialMatching", null, null,
                        null, null,
                        null, 50, 0, true).enqueue(new Callback<MatchResults>() {
                    @Override
                    public void onResponse(Call<MatchResults> call, Response<MatchResults> response) {
                        if (response.isSuccessful()) {
                            adapter.clear();
                            adapter.addAll(response.body().getMatches());
                        }
                    }

                    @Override
                    public void onFailure(Call<MatchResults> call, Throwable t) {

                    }
                });
                break;
        }
    }

    @OnClick(R.id.search_type)
    public void onBtnSearchType() {
        final BottomSheetDialog searchTypeDialog = new BottomSheetDialog(getActivity());
        View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_search_type_snomed, null);

        final TextView opcion1sillon = sheetView.findViewById(R.id.by_concept_id);
        opcion1sillon.setOnClickListener(v -> {
            ViewGroup.LayoutParams params = btnSearchType.getLayoutParams();
            tvSearchType.setText(opcion1sillon.getText());
            searchType = BY_CONCEPT_ID;
            btnSearchType.setLayoutParams(params);
            searchTypeDialog.dismiss();
        });
        final TextView opcion2sillones = sheetView.findViewById(R.id.by_description_id);
        opcion2sillones.setOnClickListener(v -> {
            ViewGroup.LayoutParams params = btnSearchType.getLayoutParams();
            tvSearchType.setText(opcion2sillones.getText());
            searchType = 2;
            btnSearchType.setLayoutParams(params);
            searchTypeDialog.dismiss();
        });
        final TextView opcion3sillones = sheetView.findViewById(R.id.by_term);
        opcion3sillones.setOnClickListener(v -> {
            ViewGroup.LayoutParams params = btnSearchType.getLayoutParams();
            tvSearchType.setText(opcion3sillones.getText());
            searchType = 3;
            btnSearchType.setLayoutParams(params);
            searchTypeDialog.dismiss();
        });


        searchTypeDialog.setContentView(sheetView);
        searchTypeDialog.show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 2) {
            search();
        } else if (newText.length() == 0) {
            adapter.clear();
        }
        return false;
    }
}
