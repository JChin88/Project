package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import io.reactivex.Observable;

public class EditDonationItemInteractor extends UseCase<String, EditDonationItemInteractor.Params> {

    private final DonationItemRepository donationItemRepository;

    @Inject
    public EditDonationItemInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                      DonationItemRepository donationItemRepository) {
        super(threadExecutor, mainThread);
        this.donationItemRepository = donationItemRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Params params) {
        return donationItemRepository.editDonationItem(params.donationItemID, params.donationItem);
    }

    public static final class Params{

        private final String donationItemID;

        private DonationItem donationItem;

        private Params(String donationItemID, DonationItem donationItem) {
            this.donationItemID = donationItemID;
            this.donationItem = donationItem;
        }

        public static Params editDonationItem(String donationItemID, DonationItem donationItem) {
            return new Params(donationItemID, donationItem);
        }
    }
}
