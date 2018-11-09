package edu.gatech.cs2340.cs2340project.presentation.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;

public class DonationItemsAdapter extends FirestoreRecyclerAdapter<DonationItem, DonationItemsAdapter.DonationItemHolder> {

    private OnItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DonationItemsAdapter(@NonNull FirestoreRecyclerOptions<DonationItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DonationItemHolder holder, int position, @NonNull DonationItem model) {
        holder.textViewDonationItemTitle.setText(model.getDonationItemName());
        holder.textViewDonationItemShortDescription.setText(model.getShortDescription());
    }

    @NonNull
    @Override
    public DonationItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donation_item,
                    viewGroup, false);
        return new DonationItemHolder(v);
    }

    class DonationItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.donation_item_name)
        TextView textViewDonationItemTitle;

        @BindView(R.id.donation_item_short_description)
        TextView textViewDonationItemShortDescription;

        public DonationItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.OnItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
