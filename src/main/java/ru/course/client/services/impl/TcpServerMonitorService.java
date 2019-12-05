package ru.course.client.services.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.product.Monitor;
import ru.course.client.services.ServerMonitorService;

import java.util.List;

@Service
@PropertySource("classpath:properties/app.properties")
@RequiredArgsConstructor
public class TcpServerMonitorService implements ServerMonitorService {

    private final OkHttpClient okHttpClient;
    private final Gson googleJson;
    @Value("${server.url}")
    private String SERVER_URL;

    @Override
    @SneakyThrows
    public List<Monitor> findAll() {
        ReleaseControllerValidator.logValidate();
        String jsonResponse = okHttpClient.newCall(new Request.Builder()
                .url(SERVER_URL + "/v1/monitors")
                .build())
                .execute()
                .body()
                .string();
        ReleaseControllerValidator.logValidate();
        return googleJson.fromJson(jsonResponse, new TypeToken<List<Monitor>>() {
        }.getType());
    }

    @Override
    @SneakyThrows
    public void save(Monitor model) {
        ReleaseControllerValidator.logValidate();
        String jsonModel = googleJson.toJson(model);
        ReleaseControllerValidator.logValidate();
        RequestBody requestBody = RequestBody.create(jsonModel, MediaType.parse("application/json; charset=utf-8"));
        ReleaseControllerValidator.logValidate();
        okHttpClient.newCall(new Request.Builder()
                .post(requestBody)
                .url(SERVER_URL + "/v1/monitors")
                .build())
                .execute();
        ReleaseControllerValidator.logValidate();
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        ReleaseControllerValidator.logValidate();
        okHttpClient.newCall(new Request.Builder()
                .delete()
                .url(SERVER_URL + "/v1/monitors/" + id)
                .build())
                .execute();
        ReleaseControllerValidator.logValidate();
    }
}
