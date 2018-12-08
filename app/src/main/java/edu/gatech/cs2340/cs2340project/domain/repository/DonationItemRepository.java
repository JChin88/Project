package edu.gatech.cs2340.cs2340project.domain.repository;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import io.reactivex.Observable;

public interface DonationItemRepository {

    Observable<String> addDonationItem(DonationItem donationItem);

    Observable<String> editDonationItem(String donationItemID, DonationItem donationItem);

    Observable<DonationItem> getDonationItem(String donationItemID);

    Observable<List<DonationItem>> getDonationItemList();

    Observable<FirestoreRecyclerOptions<DonationItem>> getDonationItemQuery(String locationName);

    void getDonationItemCollectionRef();
}
