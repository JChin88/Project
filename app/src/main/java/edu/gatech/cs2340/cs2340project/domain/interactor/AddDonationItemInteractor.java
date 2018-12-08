package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import io.reactivex.Observable;

public class AddDonationItemInteractor extends UseCase<String, AddDonationItemInteractor.Params> {

    private final DonationItemRepository donationItemRepository;

    @Inject
    public AddDonationItemInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                     DonationItemRepository donationItemRepository) {
        super(threadExecutor, mainThread);
        this.donationItemRepository = donationItemRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Params params) {
        return donationItemRepository.addDonationItem(params.donationItem);
    }

    public static final class Params {

        private final DonationItem donationItem;

        Params(DonationItem donationItem) {
            this.donationItem = donationItem;
        }

        public static Params addDonationItem(DonationItem donationItem) {
            return new Params(donationItem);
        }
    }
}
