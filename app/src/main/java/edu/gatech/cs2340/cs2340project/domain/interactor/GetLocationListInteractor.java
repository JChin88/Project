package edu.gatech.cs2340.cs2340project.domain.interactor;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.Location;

public interface GetLocationListInteractor {

    interface Callback {

        void onLocationListRetrived(List<Location> locationList);

        void onLocationListRetrievedFail(String errorMessage);

    }
}
