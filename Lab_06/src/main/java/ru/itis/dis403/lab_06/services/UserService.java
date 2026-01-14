package ru.itis.dis403.lab_06.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.dis403.lab_06.models.User;
import ru.itis.dis403.lab_06.repositories.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void addUser(User user) throws Exception {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        user.setHashPassword(
                bCrypt.encode(user.getHashPassword()));

        userRepository.addUser(user);
    }

    public User checkUser(String username, String password) throws Exception {
        String hashPassword = userRepository.getUserPasswordHash(username);

        if (hashPassword == null) return null;

        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if (!bCrypt.matches(password, hashPassword)) return null;

        return userRepository.getUserByUserName(username);
    }

}
