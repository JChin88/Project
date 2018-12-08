package edu.gatech.cs2340.cs2340project.domain.interactor;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import io.reactivex.Observable;

public class GetDonationItemFSOptionsInteractor extends UseCase<FirestoreRecyclerOptions<DonationItem>, GetDonationItemFSOptionsInteractor.Params> {

    private final DonationItemRepository donationItemRepository;

    @Inject
    public GetDonationItemFSOptionsInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                              DonationItemRepository donationItemRepository) {
        super(threadExecutor, mainThread);
        this.donationItemRepository = donationItemRepository;
    }

    @Override
    Observable<FirestoreRecyclerOptions<DonationItem>> buildUseCaseObservable(Params params) {
        return donationItemRepository.getDonationItemQuery(params.locationName);
    }

    public static final class Params {

        private final String locationName;

        private Params(String locatioName) {
            this.locationName = locatioName;
        }

        public static Params getDonationItemQuery(String locationName) {
            return new Params(locationName);
        }
    }
}
