package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import io.reactivex.Observable;

public class GetDonationItemInteractor extends UseCase<DonationItem, GetDonationItemInteractor.Params> {

    private final DonationItemRepository donationItemRepository;

    @Inject
    public GetDonationItemInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                     DonationItemRepository donationItemRepository) {
        super(threadExecutor, mainThread);
        this.donationItemRepository = donationItemRepository;
    }

    @Override
    Observable<DonationItem> buildUseCaseObservable(Params params) {
        return donationItemRepository.getDonationItem(params.donationItemID);
    }

    public static final class Params {

        private final String donationItemID;

        private Params(String donationItemID) {
            this.donationItemID = donationItemID;
        }

        public static Params getDonationItem(String donationItemID) {
            return new Params(donationItemID);
        }

    }
}
