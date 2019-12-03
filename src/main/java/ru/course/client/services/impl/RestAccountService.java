package ru.course.client.services.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.course.client.models.users.AppUser;
import ru.course.client.services.AccountService;

import java.util.Optional;

@Service
@PropertySource("classpath:properties/app.properties")
@RequiredArgsConstructor
public class RestAccountService implements AccountService {

    private final OkHttpClient okHttpClient;
    private final Gson gson;
    @Value("${server.url}")
    private String SERVER_URL;

    @Override
    @SneakyThrows
    @SuppressWarnings("all")
    public boolean isLoginAndPasswordValid(String login, String password) {
        String user = gson.toJson(AppUser.builder().login(login).password(password).build());
        RequestBody requestBody = RequestBody.create(user, MediaType.parse("application/json; charset=utf-8"));
        return gson.fromJson(okHttpClient.newCall(new Request.Builder()
                .put(requestBody)
                .url(SERVER_URL + "/v1/users/validate")
                .build())
                .execute()
                .body()
                .string(), Boolean.class);
    }

    @Override
    @SneakyThrows
    public Optional<AppUser> getAppUserByLogin(String login) {
        Response response = okHttpClient.newCall(new Request.Builder()
                .url(SERVER_URL + "/v1/users/" + login)
                .build())
                .execute();
        return response.code() == 404
                ? Optional.empty()
                : Optional.of(gson.fromJson(response.body().string(), AppUser.class));
    }

    @Override
    @SneakyThrows
    public void create(AppUser newCustomer) {
        String user = gson.toJson(newCustomer);
        RequestBody requestBody = RequestBody.create(user, MediaType.parse("application/json; charset=utf-8"));
        okHttpClient.newCall(new Request.Builder()
                .post(requestBody)
                .url(SERVER_URL + "/v1/users")
                .build())
                .execute();
    }
}
