package edu.gatech.cs2340.cs2340project.domain.repository;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import io.reactivex.Observable;

public interface DonationItemRepository {

    Observable<List<DonationItem>> getDonationItemList();

    Observable<FirestoreRecyclerOptions<DonationItem>> getDonationItemQuery(String locationName);

    void getDonationItemCollectionRef();
}
