package edu.gatech.cs2340.cs2340project.presentation.dagger.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import edu.gatech.cs2340.cs2340project.presentation.view_models.AddEditDonationItemViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view_models.DonationItemDetailsViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view_models.DonationLocationViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view.activities.util.ViewModelFactory;
import edu.gatech.cs2340.cs2340project.presentation.view_models.LocationInfoViewModel;
import edu.gatech.cs2340.cs2340project.presentation.view_models.UserViewModel;

@SuppressWarnings("WeakerAccess")
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DonationLocationViewModel.class)
    abstract ViewModel bindDonationLocationViewModel(DonationLocationViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LocationInfoViewModel.class)
    abstract ViewModel bindLocationInfoViewModel(LocationInfoViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DonationItemDetailsViewModel.class)
    abstract ViewModel bindDonationItemDetailsViewModel(DonationItemDetailsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AddEditDonationItemViewModel.class)
    abstract ViewModel bindAddDonationItemViewModel(AddEditDonationItemViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}