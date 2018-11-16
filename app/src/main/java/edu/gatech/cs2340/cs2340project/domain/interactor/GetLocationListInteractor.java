package edu.gatech.cs2340.cs2340project.domain.interactor;

/**
 * @author Hoa V Luu
 */
public interface GetLocationListInteractor {

    /**
     * callback for next action
     */
    interface Callback {

        /**
         * next action when get location success
         */
        void onLocationListRetrieved();

        /**
         *  next action when get location failed
         */
        void onLocationListRetrievedFail();

    }
}
