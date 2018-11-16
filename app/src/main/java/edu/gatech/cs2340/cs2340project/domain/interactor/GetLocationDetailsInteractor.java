package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;

/**
 * Get location detail interactor
 */
public interface GetLocationDetailsInteractor extends Interactor {

    /**
     * this use case call back
     */
    interface Callback {

        /**
         * next action when get a location success
         * @param location location from repository
         */
        void onLocationRetrieved(Location location);

        /**
         * next action when get a location failed
         * @param error error message wants to display
         */
        void onRetrievalFailed(String error);
    }

}
