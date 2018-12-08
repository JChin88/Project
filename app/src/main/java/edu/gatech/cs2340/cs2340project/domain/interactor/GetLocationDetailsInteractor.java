package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import io.reactivex.Observable;

public class GetLocationDetailsInteractor
        extends UseCase<DonationLocation, GetLocationDetailsInteractor.Params> {

    private final LocationRepository locationRepository;

    @Inject
    public GetLocationDetailsInteractor(ThreadExecutor threadExecutor,
                                        MainThread mainThread,
                                        LocationRepository locationRepository) {
        super(threadExecutor, mainThread);
        this.locationRepository = locationRepository;
    }

    @Override
    Observable<DonationLocation> buildUseCaseObservable(Params params) {
        return this.locationRepository.getLocation(params.key);
    }

    public static final class Params {

        private final String key;

        private Params(String key) {
            this.key = key;
        }

        public static Params forUser(String key) {
            return new Params(key);
        }
    }

}
