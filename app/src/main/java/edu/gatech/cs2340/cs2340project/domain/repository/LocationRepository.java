package edu.gatech.cs2340.cs2340project.domain.repository;

import java.io.InputStream;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;

public interface LocationRepository {

    void setInteractor(Interactor interactor);

    void getLocation(String key);

    void addLocations(InputStream locationDataFile);

    void getLocationList();
}
