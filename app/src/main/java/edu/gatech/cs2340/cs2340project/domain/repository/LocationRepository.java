package edu.gatech.cs2340.cs2340project.domain.repository;

import java.io.InputStream;
import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import io.reactivex.Observable;

public interface LocationRepository {

    Observable<DonationLocation> getLocation(String key);

    void addLocations(InputStream locationDataFile);

    Observable<List<DonationLocation>> getLocationList();
}
