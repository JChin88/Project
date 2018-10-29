package edu.gatech.cs2340.cs2340project.domain.interactor;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;

public interface GetLocationDetailsInteractor extends Interactor {

    interface Callback {

        void onLocationRetrieved(Location location);

        void onRetrievalFailed(String error);
    }

}
