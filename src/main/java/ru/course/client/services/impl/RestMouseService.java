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
import ru.course.client.models.product.Mouse;
import ru.course.client.services.MouseService;

import java.util.List;

@Service
@PropertySource("classpath:properties/app.properties")
@RequiredArgsConstructor
public class RestMouseService implements MouseService {
    private final OkHttpClient okHttpClient;
    private final Gson gson;
    @Value("${server.url}")
    private String SERVER_URL;

    @Override
    @SneakyThrows
    public List<Mouse> findAll() {
        String jsonResponse = okHttpClient.newCall(new Request.Builder()
                .url(SERVER_URL + "/v1/mouses")
                .build())
                .execute()
                .body()
                .string();
        return gson.fromJson(jsonResponse, new TypeToken<List<Mouse>>() {
        }.getType());
    }

    @Override
    @SneakyThrows
    public void save(Mouse model) {
        String jsonModel = gson.toJson(model);
        RequestBody requestBody = RequestBody.create(jsonModel, MediaType.parse("application/json; charset=utf-8"));
        okHttpClient.newCall(new Request.Builder()
                .post(requestBody)
                .url(SERVER_URL + "/v1/mouses")
                .build())
                .execute();
    }

    @Override
    @SneakyThrows
    public void deleteById(Long id) {
        okHttpClient.newCall(new Request.Builder()
                .delete()
                .url(SERVER_URL + "/v1/mouses/" + id)
                .build())
                .execute();
    }
}
