package com.jorgegatica.snomedbrowser;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jorgegatica.snomedbrowser.model.Child;
import com.jorgegatica.snomedbrowser.model.Concept;
import com.jorgegatica.snomedbrowser.model.Description;
import com.jorgegatica.snomedbrowser.model.Match;
import com.jorgegatica.snomedbrowser.model.Parent;
import com.jorgegatica.snomedbrowser.model.Relationship;
import com.jorgegatica.snomedbrowser.service.SnomedAPI;

import java.util.ArrayList;
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


public class SnomedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MATCH = 1;
    private static final int CONCEPT = 2;
    private static final int PARENT = 3;
    private static final int CHILD = 4;
    private static final int FINDING_SITE = 5;
    private static final int DESCRIPTION = 6;
    private final Activity activity;
    private final List items;
    private final Context context;

    public SnomedAdapter(Context context, Activity activity) {
        this.items = new ArrayList();
        this.context = context;
        this.activity = activity;
    }

    public void addAll(List items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_term)
        TextView tvTerm;
        @BindView(R.id.tv_fsn)
        TextView tvFsn;
        @BindView(R.id.cl_match)
        ConstraintLayout clMatch;

        public MatchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ConceptViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_term)
        TextView tvTerm;
        @BindView(R.id.tv_fsn)
        TextView tvFsn;
        @BindView(R.id.cl_match)
        ConstraintLayout clMatch;

        public ConceptViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case MATCH:
            view = inflater.inflate(R.layout.rv_match_preview, parent, false);
            holder = new MatchViewHolder(view);
            break;
            case CONCEPT:
                view = inflater.inflate(R.layout.rv_concept_list_item, parent, false);
                holder = new ConceptViewHolder(view);
                break;
            case PARENT:
                view = inflater.inflate(R.layout.rv_concept_parent, parent, false);
                holder = new ParentViewHolder(view);
                break;
            case DESCRIPTION:
                view = inflater.inflate(R.layout.rv_concept_descriptions, parent, false);
                holder = new ConceptDescriptionsViewHolder(view);
                break;
            case FINDING_SITE:
                view = inflater.inflate(R.layout.rv_concept_finding_site, parent, false);
                holder = new FindingSiteViewHolder(view);
                break;
            case CHILD:
                view = inflater.inflate(R.layout.rv_concept_child, parent, false);
                holder = new ChildViewHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder.getItemViewType() == CONCEPT){
            Concept item = (Concept) items.get(position);
            ConceptViewHolder holder = (ConceptViewHolder) viewHolder;
            holder.tvTerm.setText(item.getDefaultTerm());
            holder.tvFsn.setText(item.getFsn());
            holder.clMatch.setOnClickListener(v ->{
                Intent i = new Intent(activity, ConceptActivity.class);
                i.putExtra("conceptId", item.getConceptId());
                activity.startActivity(i);
            });

        } else if (viewHolder.getItemViewType() == MATCH) {
            Match item = (Match) items.get(position);
            MatchViewHolder holder = (MatchViewHolder) viewHolder;
            holder.tvFsn.setText(item.getFsn());
            holder.tvTerm.setText(item.getTerm());
            holder.clMatch.setOnClickListener(v -> {
                Intent i = new Intent(activity, ConceptActivity.class);
                i.putExtra("conceptId", item.getConceptId());
                activity.startActivity(i);

            });

        } else if (viewHolder.getItemViewType() == DESCRIPTION) {
            Description item = (Description) items.get(position);
            ConceptDescriptionsViewHolder holder = (ConceptDescriptionsViewHolder) viewHolder;
            holder.tvLang.setText(item.getLang());
            holder.tvTerm.setText(item.getTerm());
            holder.tvTerm.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("description", item.getDescriptionId()+" | "+item.getTerm()+" |");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copiado al portapapeles ", Toast.LENGTH_SHORT).show();
            });

        } else if (viewHolder.getItemViewType() == FINDING_SITE) {
            Relationship item = (Relationship) items.get(position);
            FindingSiteViewHolder holder = (FindingSiteViewHolder) viewHolder;
            holder.tvStatedRel.setText(item.getType().getDefaultTerm() + " → " + item.getTarget().getDefaultTerm());

        } else if (viewHolder.getItemViewType() == PARENT) {
            Parent item = (Parent) items.get(position);
            ParentViewHolder holder = (ParentViewHolder) viewHolder;
            holder.tvParent.setText(item.getDefaultTerm());
            holder.tvParent.setOnLongClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("concept", item.getConceptId()+" | "+item.getDefaultTerm()+" |");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copiado al portapapeles ", Toast.LENGTH_SHORT).show();
                return true;
            });

            holder.tvParent.setOnClickListener(v -> {
                if (holder.expanded) {
                    holder.rvParents.setVisibility(View.GONE);
                    holder.expanded = false;
                } else {
                    if (holder.dataLoaded) {
                        holder.rvParents.setVisibility(View.VISIBLE);
                        holder.expanded = true;
                    } else {
                        SnomedAPI.createDescriptionService().findAllParentsOfConcept(EDITION, RELEASE, item.getConceptId(), null).enqueue(new Callback<List<Parent>>() {
                            @Override
                            public void onResponse(Call<List<Parent>> call, Response<List<Parent>> response) {
                                SnomedAdapter adapter = new SnomedAdapter(context, activity);
                                holder.rvParents.setLayoutManager(new LinearLayoutManager(context));
                                adapter.addAll(response.body());
                                holder.rvParents.setAdapter(adapter);
                                holder.rvParents.setVisibility(View.VISIBLE);
                                holder.dataLoaded = true;
                                holder.expanded = true;

                            }

                            @Override
                            public void onFailure(Call<List<Parent>> call, Throwable t) {

                            }
                        });
                    }
                }

            });


        } else if (viewHolder.getItemViewType() == CHILD) {
            Child item = (Child) items.get(position);
            ChildViewHolder holder = (ChildViewHolder) viewHolder;
            if (item.getStatedDescendants() > 0) {
                holder.tvChildren.setText(item.getDefaultTerm() + " (" + item.getStatedDescendants() + ")");
                holder.tvChildren.setOnClickListener(v -> {
                    if (holder.expanded) {
                        holder.rvChildren.setVisibility(View.GONE);
                        holder.expanded = false;
                    } else {
                        if (holder.dataLoaded) {
                            holder.rvChildren.setVisibility(View.VISIBLE);
                            holder.expanded = true;
                        } else {
                            SnomedAdapter adapter = new SnomedAdapter(context, activity);
                            holder.rvChildren.setLayoutManager(new LinearLayoutManager(context));
                            holder.rvChildren.setAdapter(adapter);
                            SnomedAPI.createDescriptionService().findAllChildrenOfConcept(EDITION, RELEASE, item.getConceptId(), null)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(c -> {
                                        adapter.addAll(c);

                                        holder.rvChildren.setVisibility(View.VISIBLE);
                                        holder.dataLoaded = true;
                                        holder.expanded = true;


                                    });

                        }
                    }
                });
            } else {
                holder.tvChildren.setText(item.getDefaultTerm());
            }

        }
    }

    public void add(Object o) {
        this.items.add(o);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Match) {
            return MATCH;
        } else if (items.get(position) instanceof Concept) {
            return CONCEPT;
        } else if (items.get(position) instanceof Relationship) {
            return FINDING_SITE;
        } else if (items.get(position) instanceof Description) {
            return DESCRIPTION;
        } else if (items.get(position) instanceof Parent) {
            return PARENT;
        } else if (items.get(position) instanceof Child) {
            return CHILD;
        } else {
            return -1;
        }

    }

    public class ConceptDescriptionsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lang)
        TextView tvLang;
        @BindView(R.id.tv_term)
        TextView tvTerm;

        public ConceptDescriptionsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class FindingSiteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_stated_rel)
        TextView tvStatedRel;

        public FindingSiteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_children)
        TextView tvChildren;
        @BindView(R.id.rv_children)
        RecyclerView rvChildren;
        boolean expanded = false;
        boolean dataLoaded = false;

        public ChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_parent)
        TextView tvParent;
        @BindView(R.id.rv_parents)
        RecyclerView rvParents;
        boolean expanded = false;
        boolean dataLoaded = false;

        public ParentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
