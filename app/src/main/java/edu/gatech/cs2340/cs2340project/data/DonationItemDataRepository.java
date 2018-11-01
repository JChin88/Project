package edu.gatech.cs2340.cs2340project.data;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;

public class DonationItemDataRepository implements DonationItemRepository {

    private FirebaseFirestore db;
    private Interactor interactor;

    public DonationItemDataRepository() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void setInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void getListDonationItem() {

    }

    @Override
    public void getDonationItemCollectionRef() {
        CollectionReference donationItemCollectionRef = db.collection("Donation Items");
        if (donationItemCollectionRef != null) {
            interactor.onNext(donationItemCollectionRef);
        } else {
            interactor.onError("Retrieved the collection of donation items failed");
        }
    }
}
