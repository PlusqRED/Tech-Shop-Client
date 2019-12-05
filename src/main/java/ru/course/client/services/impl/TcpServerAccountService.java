package ru.course.client.services.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.users.AppUser;
import ru.course.client.services.ServerAccountService;

import java.util.Optional;

@Service
@PropertySource("classpath:properties/app.properties")
@RequiredArgsConstructor
public class TcpServerAccountService implements ServerAccountService {

    private final OkHttpClient okHttpClient;
    private final Gson googleJson;
    @Value("${server.url}")
    private String SERVER_URL;

    @Override
    @SneakyThrows
    @SuppressWarnings("all")
    public boolean isLoginAndPasswordValid(String login, String password) {
        ReleaseControllerValidator.logValidate();
        String user = googleJson.toJson(AppUser.builder().login(login).password(password).build());
        RequestBody requestBody = RequestBody.create(user, MediaType.parse("application/json; charset=utf-8"));
        ReleaseControllerValidator.logValidate();
        return googleJson.fromJson(okHttpClient.newCall(new Request.Builder()
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
        ReleaseControllerValidator.logValidate();
        Response response = okHttpClient.newCall(new Request.Builder()
                .url(SERVER_URL + "/v1/users/" + login)
                .build())
                .execute();
        ReleaseControllerValidator.logValidate();
        return response.code() == 404
                ? Optional.empty()
                : Optional.of(googleJson.fromJson(response.body().string(), AppUser.class));
    }

    @Override
    @SneakyThrows
    public void create(AppUser newCustomer) {
        ReleaseControllerValidator.logValidate();
        String user = googleJson.toJson(newCustomer);
        ReleaseControllerValidator.logValidate();
        RequestBody requestBody = RequestBody.create(user, MediaType.parse("application/json; charset=utf-8"));
        ReleaseControllerValidator.logValidate();
        okHttpClient.newCall(new Request.Builder()
                .post(requestBody)
                .url(SERVER_URL + "/v1/users")
                .build())
                .execute();
    }
}
