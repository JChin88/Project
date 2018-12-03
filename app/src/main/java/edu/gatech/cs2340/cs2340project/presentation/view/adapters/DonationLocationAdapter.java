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

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.R;
import edu.gatech.cs2340.cs2340project.domain.model.Location;

/**
 * Main adapter for list of donation item
 */
public class DonationLocationAdapter
        extends FirestoreRecyclerAdapter<Location, DonationLocationAdapter.DonationLocationHolder> {

    private OnItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options the firestore options
     */
    public DonationLocationAdapter(@NonNull FirestoreRecyclerOptions<Location> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DonationLocationHolder holder, int position,
                                    @NonNull Location model) {
        holder.donationLocationName.setText(model.getName());
        holder.donationLocationType.setText(model.getType());
    }

    @NonNull
    @Override
    public DonationLocationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v  = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_donation_location,
                viewGroup, false);
        return new DonationLocationHolder(v);
    }

    final class DonationLocationHolder extends RecyclerView.ViewHolder {

        final TextView donationLocationName;
        final TextView donationLocationType;

        private DonationLocationHolder(@NonNull View itemView) {
            super(itemView);
            donationLocationName = itemView.findViewById(R.id.card_view_donation_location_name);
            donationLocationType = itemView.findViewById(R.id.card_view_donation_location_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if ((position != RecyclerView.NO_POSITION) && (listener != null)) {
                        listener.OnItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        /**
         * action when user click
         * @param documentSnapshot snapshot can convert to donation item object
         * @param position the position where user clicked
         */
        void OnItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    /**
     *
     * @param listener listener for item
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
