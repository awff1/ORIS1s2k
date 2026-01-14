package ru.itis.dis403.lab_05.service;

import ru.itis.dis403.lab_05.model.User;
import ru.itis.dis403.lab_05.repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void register(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Заполните все поля");
        }

        if (password.length() < 7) {
            throw new IllegalArgumentException("Пароль должен содержать не менее 7 символов");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new IllegalStateException("Пользователь с таким именем уже существует");
        }

        User user = new User(username, password);
        if (!userRepository.addUser(user)) {
            throw new RuntimeException("Ошибка при сохранении пользователя");
        }
    }
}
