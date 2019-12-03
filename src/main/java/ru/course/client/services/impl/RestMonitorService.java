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
import ru.course.client.models.product.Monitor;
import ru.course.client.services.MonitorService;

import java.util.List;

@Service
@PropertySource("classpath:properties/app.properties")
@RequiredArgsConstructor
public class RestMonitorService implements MonitorService {

    private final OkHttpClient okHttpClient;
    private final Gson gson;
    @Value("${server.url}")
    private String SERVER_URL;

    @Override
    @SneakyThrows
    public List<Monitor> findAll() {
        String jsonResponse = okHttpClient.newCall(new Request.Builder()
                .url(SERVER_URL + "/v1/monitors")
                .build())
                .execute()
                .body()
                .string();
        return gson.fromJson(jsonResponse, new TypeToken<List<Monitor>>() {
        }.getType());
    }

    @Override
    @SneakyThrows
    public void save(Monitor model) {
        String jsonModel = gson.toJson(model);
        RequestBody requestBody = RequestBody.create(jsonModel, MediaType.parse("application/json; charset=utf-8"));
        okHttpClient.newCall(new Request.Builder()
                .post(requestBody)
                .url(SERVER_URL + "/v1/monitors")
                .build())
                .execute();
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        okHttpClient.newCall(new Request.Builder()
                .delete()
                .url(SERVER_URL + "/v1/monitors/" + id)
                .build())
                .execute();
    }
}
