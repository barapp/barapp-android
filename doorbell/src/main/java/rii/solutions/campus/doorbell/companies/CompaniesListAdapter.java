package rii.solutions.campus.doorbell.companies;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rii.solutions.campus.data.CampusMember;
import rii.solutions.campus.doorbell.R;
import rii.solutions.campus.doorbell.databinding.CompaniesListItemBinding;
import rii.solutions.campus.doorbell.utils.MaterialColorUtils;

/**
 * List of companies
 * Currently {@link CampusMember#companyName} used
 * instead {@link rii.solutions.campus.data.CampusCompany} object,
 * this is going to be changed in future
 *
 * Created by rimmer on 05.06.2017.
 */

final class CompaniesListAdapter extends RecyclerView.Adapter<CompaniesListAdapter.CompaniesViewHolder> {

    private final List<CampusMember> items;
    private LayoutInflater inflater;

    CompaniesListAdapter(List<CampusMember> response) {
        this.items = response;
    }

    static class CompaniesViewHolder extends RecyclerView.ViewHolder {

        private final CompaniesListItemBinding binding;

        CompaniesViewHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
            this.binding.logo.setBackgroundColor(getRandomColor(itemView.getContext()));
        }
    }

    private static int getRandomColor(Context context) {
        return MaterialColorUtils.getRandomMaterialColor(context, MaterialColorUtils.TYPE_COLOR_800);
    }

    @Override
    public CompaniesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        View viewRoot = inflater.inflate(R.layout.companies_list_item, parent, false);
        return new CompaniesViewHolder(viewRoot);
    }

    @Override
    public void onBindViewHolder(CompaniesViewHolder holder, int position) {
        holder.binding.setCompany(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
