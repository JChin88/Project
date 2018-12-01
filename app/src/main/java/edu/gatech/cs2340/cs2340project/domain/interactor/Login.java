package edu.gatech.cs2340.cs2340project.domain.interactor;

import javax.inject.Inject;

import edu.gatech.cs2340.cs2340project.domain.executor.MainThread;
import edu.gatech.cs2340.cs2340project.domain.executor.ThreadExecutor;
import edu.gatech.cs2340.cs2340project.domain.repository.UserRepository;
import io.reactivex.Observable;

public class Login extends UseCase<String, Login.Params> {

    private final UserRepository userRepository;

    @Inject
    public Login(ThreadExecutor threadExecutor, MainThread mainThread, UserRepository userRepository) {
        super(threadExecutor, mainThread);
        this.userRepository = userRepository;
    }

    @Override
    Observable<String> buildUseCaseObservable(Params params) {
        return this.userRepository.login(params.email, params.password);
    }

    public static final class Params {

        private final String email;

        private final String password;

        public Params(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public static Params forUser(String email, String password) {
            return new Params(email, password);
        }
    }
}
