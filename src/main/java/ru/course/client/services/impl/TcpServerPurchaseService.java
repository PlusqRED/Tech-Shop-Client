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
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.product.Product;
import ru.course.client.models.product.commons.Purchase;
import ru.course.client.models.users.AppUser;
import ru.course.client.services.ServerPurchaseService;

import java.util.List;

@PropertySource("classpath:properties/app.properties")
@RequiredArgsConstructor
@Service

public class TcpServerPurchaseService implements ServerPurchaseService {
    private final Gson gson;
    private final OkHttpClient okHttpClient;
    @Value("${server.url}")
    private String SERVER_URL;

    @Override
    @SneakyThrows
    public void save(Long productId, Long appUserId) {
        ControllerValidator.checkVm();
        String jsonModel = gson.toJson(Purchase.builder()
                .appUser(AppUser.builder().id(appUserId).build())
                .product(Product.builder().id(productId).build()));
        ReleaseControllerValidator.logValidate();
        RequestBody requestBody = RequestBody.create(jsonModel, MediaType.parse("application/json; charset=utf-8"));
        ControllerValidator.checkVm();
        okHttpClient.newCall(new Request.Builder()
                .post(requestBody)
                .url(SERVER_URL + "/v1/purchases")
                .build())
                .execute();
    }

    @Override
    @SneakyThrows
    public List<Purchase> findAll() {
        ReleaseControllerValidator.logValidate();
        String jsonResponse = okHttpClient.newCall(new Request.Builder()
                .url(SERVER_URL + "/v1/purchases")
                .build())
                .execute()
                .body()
                .string();
        ControllerValidator.checkVm();
        return gson.fromJson(jsonResponse, new TypeToken<List<Purchase>>() {
        }.getType());
    }
}
