package edu.gatech.cs2340.cs2340project.domain.repository;

import java.io.InputStream;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.Interactor;

/**
 * @author Hoa V Luu
 */
public interface LocationRepository {

    /**
     * Set interactor for the repository to pass the data back
     * @param interactor interactor for next action
     */
    void setInteractor(Interactor interactor);

    /**
     *
     * @param key key of location wanted to get
     */
    void getLocation(String key);

    /**
     * add location to database with location file
     * @param locationDataFile location file want to add
     */
    void addLocations(InputStream locationDataFile);

    /**
     * get location list
     */
    void getLocationList();
}
