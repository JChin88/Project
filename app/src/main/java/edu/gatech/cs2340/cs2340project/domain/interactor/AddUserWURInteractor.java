package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import io.reactivex.Observable;

//Add user with user rights
public class AddUserWURInteractor extends UseCase<String, AddUserWURInteractor.Params> {

    private final UserRepository userRepository;

    @Inject
    public AddUserWURInteractor(ThreadExecutor threadExecutor, MainThread mainThread,
                                UserRepository userRepository) {
        super(threadExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Params params) {
        return this.userRepository.addUser(params.userRights);
    }

    public static final class Params {

        private UserRights userRights;

        public Params(UserRights userRights) {
            this.userRights = userRights;
        }

        public static Params addUserWUR(UserRights userRights) {
            return new Params(userRights);
        }
    }
}
