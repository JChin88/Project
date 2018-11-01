package edu.gatech.cs2340.cs2340project.domain.interactor.Impl;

import com.google.firebase.firestore.CollectionReference;

import edu.gatech.cs2340.cs2340project.domain.executor.Executor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.interactor.GetDonationItemListInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.AbstractInteractor;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;

public class GetDonationItemListInteractorImpl extends AbstractInteractor implements GetDonationItemListInteractor {

    GetDonationItemListInteractor.Callback mCallback;
    DonationItemRepository mDonationItemRepository;

    public GetDonationItemListInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                             Callback mCallback, DonationItemRepository mDonationItemRepository) {
        super(threadExecutor, mainThread);
        this.mCallback = mCallback;
        this.mDonationItemRepository = mDonationItemRepository;
    }

    @Override
    public void onError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievedCollectionRefFailed(errorMessage);
            }
        });
    }

    @Override
    public void onNext(final Object params) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievedCollectionRef((CollectionReference) params);
            }
        });
    }

    @Override
    public void run() {
        mDonationItemRepository.getDonationItemCollectionRef();
    }
}
