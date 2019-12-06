package ru.course.client.services;

import ru.course.client.models.users.AppUser;

import java.util.Optional;

public interface ServerAccountService {

    boolean isLoginAndPasswordValid(String login, String password);

    Optional<AppUser> getAppUserByLogin(String login);

    void create(AppUser newCustomer);
}
