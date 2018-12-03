package edu.gatech.cs2340.cs2340project.data;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;
import java.util.NoSuchElementException;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import io.reactivex.Observable;

@Singleton
public class DonationItemDataRepository implements DonationItemRepository {

    private FirebaseFirestore db;
    private CollectionReference donationItemRef;

    @Inject
    public DonationItemDataRepository() {
        this.db = FirebaseFirestore.getInstance();
        this.donationItemRef = db.collection("Donation Items");
    }

    @Override
    public Observable<List<DonationItem>> getDonationItemList() {
        return Observable.create(emitter -> {

        });
    }

    @Override
    public Observable<FirestoreRecyclerOptions<DonationItem>> getDonationItemQuery(String locationName) {
        return Observable.create(emitter -> {
            Query query = donationItemRef.whereEqualTo("locationName", locationName)
                    .orderBy("donationItemName", Query.Direction.ASCENDING);
            FirestoreRecyclerOptions options = new FirestoreRecyclerOptions.Builder<DonationItem>()
                    .setQuery(query, DonationItem.class)
                    .build();
            emitter.onNext(options);
            emitter.onComplete();
        });
    }

    @Override
    public void getDonationItemCollectionRef() {
        CollectionReference donationItemCollectionRef = db.collection("Donation Items");
//        if (donationItemCollectionRef != null) {
//            interactor.onNext(donationItemCollectionRef);
//        } else {
//            interactor.onError("Retrieved the collection of donation items failed");
//        }
    }
}
