package edu.gatech.cs2340.cs2340project.data;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;

/**
 * Repository for donation item
 */
public class DonationItemDataRepository implements DonationItemRepository {

    private final FirebaseFirestore db;

    /**
     * Constructor for DI repository
     */
    public DonationItemDataRepository() {
        this.db = FirebaseFirestore.getInstance();
    }

    /**
     * Set the interactor for repository
     * @param interactor the interactor want to run
     */
    public void setInteractor(Interactor interactor) {
        Interactor interactor1 = interactor;
    }

//    @Override
//    public void getListDonationItem() {
//
//    }

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
