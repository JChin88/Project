package edu.gatech.cs2340.cs2340project.domain.interactor;

import com.google.firebase.firestore.CollectionReference;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;

public interface GetDonationItemListInteractor extends Interactor {

    interface Callback {

        void onRetrievedCollectionRef(CollectionReference collectionReference);

        void onRetrievedCollectionRefFailed(String erroMessage);

    }

}
