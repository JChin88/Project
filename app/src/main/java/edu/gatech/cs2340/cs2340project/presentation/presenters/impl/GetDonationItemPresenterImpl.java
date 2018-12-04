package edu.gatech.cs2340.cs2340project.presentation.presenters.impl;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.GetDonationItemInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.presenters.contracts.GetDonationItemPresenter;

public class GetDonationItemPresenterImpl implements GetDonationItemPresenter {

    private GetDonationItemView getDonationItemView;
    private final GetDonationItemInteractor getDonationItemInteractor;

    @Inject
    public GetDonationItemPresenterImpl(GetDonationItemInteractor getDonationItemInteractor) {
        this.getDonationItemInteractor = getDonationItemInteractor;
    }

    @Override
    public void setView(GetDonationItemView getDonationItemView) {
        this.getDonationItemView = getDonationItemView;
    }

    @Override
    public void getDonationItem(String donationItemID) {
        getDonationItemView.showProgress();
        getDonationItemInteractor.execute(new GetDonationItemObserver(),
                GetDonationItemInteractor.Params.getDonationItem(donationItemID));
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

    private class GetDonationItemObserver extends DefaultObserver<DonationItem> {

        @Override
        public void onNext(DonationItem donationItem) {
            super.onNext(donationItem);
            getDonationItemView.hideProgress();
            getDonationItemView.displayDonationItemDetails(donationItem);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            getDonationItemView.hideProgress();
            getDonationItemView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            super.onComplete();
            getDonationItemView.hideProgress();
        }
    }
}
