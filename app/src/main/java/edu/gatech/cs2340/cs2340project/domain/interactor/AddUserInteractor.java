package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.model.UserRights;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import io.reactivex.Observable;

public class AddUserInteractor extends UseCase<String, AddUserInteractor.Params> {

    private final UserRepository userRepository;

    @Inject
    public AddUserInteractor(ThreadExecutor threadExecutor,
                             MainThread mainThread, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Params params) {
        return this.userRepository.addUser(params.name, params.email,
                params.password, params.userRights);
    }

    public static final class Params {

        private final String name;

        private final String email;

        private final String password;

        private final UserRights userRights;

        private Params(String name, String email, String password, UserRights userRights) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.userRights = userRights;
        }

        public static Params addUser(String name, String email,
                                     String password, UserRights  userRights) {
            return new Params(name, email, password, userRights);
        }
    }
}
