package edu.gatech.cs2340.cs2340project.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.Location;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import io.reactivex.Observable;

public class GetLocationListInteractor extends UseCase<List<Location>, Void> {

    private final LocationRepository locationRepository;

    @Inject
    public GetLocationListInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                     LocationRepository locationRepository) {
        super(threadExecutor, mainThread);
        this.locationRepository = locationRepository;
    }

    @Override
    Observable<List<Location>> buildUseCaseObservable(Void aVoid) {
        return locationRepository.getLocationList();
    }
}
