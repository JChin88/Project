package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.User;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import io.reactivex.Observable;

public class GetUserInfoInteractor extends UseCase<User, GetUserInfoInteractor.Params> {

    private final UserRepository userRepository;

    @Inject
    public GetUserInfoInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                 UserRepository userRepository) {
        super(threadExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    Observable<User> buildUseCaseObservable(Params params) {
        return userRepository.getUser(params.id);
    }

    public static final class Params {

        private final String id;

        public Params(String id) {
            this.id = id;
        }

        public static Params forUser(String id) {
            return new Params(id);
        }
    }

}
