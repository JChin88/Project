package edu.gatech.cs2340.cs2340project.data;

import android.support.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.SetOptions;

import java.util.List;
import java.util.NoSuchElementException;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import io.reactivex.Observable;

@Singleton
public class DonationItemDataRepository implements DonationItemRepository {

    private final FirebaseFirestore db;
    private CollectionReference donationItemRef;

    @Inject
    public DonationItemDataRepository() {
        this.db = FirebaseFirestore.getInstance();
        this.donationItemRef = db.collection("Donation Items");
    }

    @Override
    public Observable<DonationItem> getDonationItem(String donationItemID) {
        return Observable.create(emitter -> {
            DocumentReference documentReference = donationItemRef.document(donationItemID);
            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        DonationItem donationItem = documentSnapshot.toObject(DonationItem.class);
                        emitter.onNext(donationItem);
                        emitter.onComplete();
                    } else {
                        NoSuchElementException noSuchElementException
                                = new NoSuchElementException("The donation item doesn't exit");
                        emitter.onError(noSuchElementException);
                    }
                }
            });
        });
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

    @Override
    public Observable<String> addDonationItem(DonationItem donationItem) {
        return Observable.create(emitter -> {
            donationItemRef.add(donationItem).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    emitter.onNext("Add Donation Item Succeed");
                    emitter.onComplete();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    emitter.onError(e);
                }
            });
        });
    }

    @Override
    public Observable<String> editDonationItem(String donationItemID, DonationItem donationItem) {
        return Observable.create(emitter -> {
            donationItemRef.document(donationItemID).set(donationItem, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            emitter.onNext("Edit Donation Item Succeed");
                            emitter.onComplete();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    emitter.onError(e);
                }
            });
        });
    }
}
