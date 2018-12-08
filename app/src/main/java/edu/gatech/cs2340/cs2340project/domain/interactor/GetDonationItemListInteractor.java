package edu.gatech.cs2340.cs2340project.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import io.reactivex.Observable;

public class GetDonationItemListInteractor extends UseCase<List<DonationItem>, Void> {

    private final DonationItemRepository donationItemRepository;

    @Inject
    public GetDonationItemListInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                         DonationItemRepository donationItemRepository) {
        super(threadExecutor, mainThread);
        this.donationItemRepository = donationItemRepository;
    }

    @Override
    Observable<List<DonationItem>> buildUseCaseObservable(Void aVoid) {
        return donationItemRepository.getDonationItemList();
    }
}
