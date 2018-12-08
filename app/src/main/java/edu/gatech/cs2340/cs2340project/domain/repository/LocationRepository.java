package edu.gatech.cs2340.cs2340project.domain.repository;

import java.io.InputStream;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.Location;
import io.reactivex.Observable;

public interface LocationRepository {

<<<<<<< HEAD
    void getLocation(String key);
=======
    Observable<Location> getLocation(String key);
>>>>>>> 3ad50de6ce4698a0e53613b1e3474ea7b840570f

    void addLocations(InputStream locationDataFile);

    Observable<List<Location>> getLocationList();
}
