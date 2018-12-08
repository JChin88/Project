package edu.gatech.cs2340.cs2340project.domain.interactor;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationLocation;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import io.reactivex.Observable;

public class GetLocationOptionsInteractor extends UseCase<FirestoreRecyclerOptions<DonationLocation>, Void> {

    private final LocationRepository locationRepository;

    @Inject
    public GetLocationOptionsInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                        LocationRepository locationRepository) {
        super(threadExecutor, mainThread);
        this.locationRepository = locationRepository;
    }

    @Override
    Observable<FirestoreRecyclerOptions<DonationLocation>> buildUseCaseObservable(Void aVoid) {
        return locationRepository.getLocationOptions();
    }
}
