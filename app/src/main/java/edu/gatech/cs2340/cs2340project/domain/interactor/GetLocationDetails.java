package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;

public interface GetLocationDetails extends Interactor {

    interface CallBack {

        void onLocationRetrieved(Location location);

        void onRetrievalFailed(String error);
    }

}
