package edu.gatech.cs2340.cs2340project.domain.repository;

import java.util.List;

import edu.gatech.cs2340.cs2340project.domain.model.Location;

public interface LocationRepository {

    Location getLocation(Integer key);

    List<Location> getLocationList();
}
