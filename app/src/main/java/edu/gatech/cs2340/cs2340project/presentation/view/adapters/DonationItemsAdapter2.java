package edu.gatech.cs2340.cs2340project.presentation.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;

public class DonationItemsAdapter2 extends RecyclerView.Adapter<DonationItemsAdapter2.DonationItemHolder> {

    Context baseContext;
    List<DonationItem> listDI;

    public DonationItemsAdapter2(Activity searchActivity, List<DonationItem> listDI) {
        baseContext = searchActivity.getBaseContext();
        this.listDI = listDI;
    }

    public DonationItemsAdapter2(Context context, List<DonationItem> listDI) {
        baseContext = context;
        this.listDI = listDI;
    }

    @NonNull
    @Override
    public DonationItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(baseContext);
        View view = inflater.inflate(R.layout.donation_item, viewGroup, false);
        return new DonationItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationItemHolder donationItemHolder, int i) {
        donationItemHolder.textViewDonationItemTitle.setText(listDI.get(i).getDonationItemName());
        donationItemHolder.textViewDonationItemShortDescription.setText(listDI.get(i).getShortDescription());

    }

    @Override
    public int getItemCount() {
        return listDI.size();
    }

    class DonationItemHolder extends RecyclerView.ViewHolder {

        TextView textViewDonationItemTitle;
        TextView textViewDonationItemShortDescription;

        public DonationItemHolder(@NonNull View itemView) {
            super(itemView);
            textViewDonationItemTitle = itemView.findViewById(R.id.donation_item_name);
            textViewDonationItemShortDescription = itemView.findViewById(R.id.donation_item_short_description);
        }
    }
}
