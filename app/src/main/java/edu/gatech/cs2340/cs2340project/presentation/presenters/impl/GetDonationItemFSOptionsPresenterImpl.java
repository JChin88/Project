package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetDonationItemFSOptionsInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetDonationItemFSOptionsPresenter;

public class GetDonationItemFSOptionsPresenterImpl implements GetDonationItemFSOptionsPresenter {

    private final GetDonationItemFSOptionsInteractor getDonationItemQueryInteractor;
    private GetDonationItemFSOptionsPresenter.GetDonationItemFSOptionsView getDonationItemFSOptionsView;

    @Inject
    public GetDonationItemFSOptionsPresenterImpl(GetDonationItemFSOptionsInteractor getDonationItemQueryInteractor) {
        this.getDonationItemQueryInteractor = getDonationItemQueryInteractor;
    }

    @Override
    public void setView(GetDonationItemFSOptionsView getDonationItemFSOptionsView) {
        this.getDonationItemFSOptionsView = getDonationItemFSOptionsView;
    }

    @Override
    public void getDonationItemFSOptions(String locationName) {
        getDonationItemFSOptionsView.showProgress();
        this.getDonationItemQueryInteractor.execute(new GetDonationItemFSOptionsObserver(),
                GetDonationItemFSOptionsInteractor.Params.getDonationItemQuery(locationName));
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    private final class GetDonationItemFSOptionsObserver extends DefaultObserver<FirestoreRecyclerOptions<DonationItem>> {

        @Override
        public void onNext(FirestoreRecyclerOptions<DonationItem> options) {
            super.onNext(options);
            getDonationItemFSOptionsView.hideProgress();
            getDonationItemFSOptionsView.setUpRecyclerView(options);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getDonationItemFSOptionsView.hideProgress();
            getDonationItemFSOptionsView.showError(e.getMessage());
        }
    }

}
