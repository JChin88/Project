package edu.gatech.cs2340.cs2340project.domain.interactor;

import com.google.firebase.firestore.CollectionReference;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;

/**
 * Interactor to get donation item
 */
public interface GetDonationItemListInteractor extends Interactor {

    /**
     * callback for this use case
     */
    interface Callback {

        /**
         * next action when retrieve a collection success
         * @param collectionReference collection holds donation items
         */
        void onRetrievedCollectionRef(CollectionReference collectionReference);

        /**
         * next action when retrieve a collection failed
         * @param erroMessage error message wnat to display
         */
        void onRetrievedCollectionRefFailed(String erroMessage);

    }

}
