package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import io.reactivex.Observable;

public class GetCurrentUserInteractor extends UseCase<User, Void> {

    private final UserRepository userRepository;

    @Inject
    public GetCurrentUserInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                    UserRepository userRepository) {
        super(threadExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    Observable<User> buildUseCaseObservable(Void aVoid) {
        return userRepository.getCurrentUser();
    }
}
