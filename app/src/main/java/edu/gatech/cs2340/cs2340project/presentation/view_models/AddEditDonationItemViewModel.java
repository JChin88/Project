package edu.gatech.cs2340.cs2340project.presentation.view_models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.interactor.AddDonationItemInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.EditDonationItemInteractor;
import edu.gatech.cs2340.cs2340project.domain.interactor.base.DefaultObserver;
import edu.gatech.cs2340.cs2340project.domain.model.DonationItem;
import edu.gatech.cs2340.cs2340project.presentation.view_models.common.Response;

public class AddEditDonationItemViewModel extends ViewModel {

    private AddDonationItemInteractor addDonationItemInteractor;

    private EditDonationItemInteractor editDonationItemInteractor;

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    @Inject
    public AddEditDonationItemViewModel(AddDonationItemInteractor addDonationItemInteractor,
                                        EditDonationItemInteractor editDonationItemInteractor) {
        this.addDonationItemInteractor = addDonationItemInteractor;
        this.editDonationItemInteractor = editDonationItemInteractor;
    }

    public void clean() {
        addDonationItemInteractor.dispose();
        editDonationItemInteractor.dispose();
    }

    public void addDonationItem(DonationItem donationItem) {
        response.setValue(Response.loading());
        addDonationItemInteractor.execute(new AddEditDonationItemObserver(),
                AddDonationItemInteractor.Params.addDonationItem(donationItem));
    }

    public void editDonationItem(String donationItemID, DonationItem donationItem) {
        response.setValue(Response.loading());
        editDonationItemInteractor.execute(new AddEditDonationItemObserver(),
                EditDonationItemInteractor.Params.editDonationItem(donationItemID, donationItem));
    }

    public MutableLiveData<Response> response() {
        return response;
    }

    private final class AddEditDonationItemObserver<T> extends DefaultObserver<T> {
        @Override
        public void onNext(T successMessage) {
            response.setValue(Response.success(successMessage));
        }

        @Override
        public void onError(Throwable e) {
            response.setValue(Response.error(e));
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }
    }
}
