package edu.gatech.cs2340.cs2340project.presentation.dagger.module;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import edu.gatech.cs2340.cs2340project.data.DonationItemDataRepository;
import edu.gatech.cs2340.cs2340project.data.LocationDataRepository;
import edu.gatech.cs2340.cs2340project.data.UserDataRepository;
import edu.gatech.cs2340.cs2340project.domain.executor.Impl.JobExecutor;
import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.repository.DonationItemRepository;
import edu.gatech.cs2340.cs2340project.domain.repository.LocationRepository;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import edu.gatech.cs2340.cs2340project.presentation.dagger.component.AppComponent;
import edu.gatech.cs2340.cs2340project.threading.MainThreadImpl;

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your
 * Application & Activities are provided into your graph for you.
 * {@link AppComponent}.
 */
@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);

    @Binds
    @Singleton
    abstract ThreadExecutor bindThreadExecutor(JobExecutor jobExecutor);

    @Binds
    @Singleton
    abstract MainThread bindMainThread(MainThreadImpl mainThread);

    @Binds
    @Singleton
    abstract UserRepository bindUserRepository(UserDataRepository userDataRepository);

    @Binds
    @Singleton
    abstract LocationRepository bindLocationRepository(LocationDataRepository locationDataRepository);

    @Binds
    @Singleton
    abstract DonationItemRepository bindDonationItemRepository(DonationItemDataRepository donationItemDataRepository);

    @Provides
    @Singleton
    static FirebaseFirestore provideFirestore() {
        FirebaseFirestore.setLoggingEnabled(true);
        return FirebaseFirestore.getInstance();
    }

    @Provides
    @Singleton
    static FirebaseAuth provideFireBaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
