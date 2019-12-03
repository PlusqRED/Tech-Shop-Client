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
import ru.course.client.models.product.Product;
import ru.course.client.models.product.commons.Purchase;
import ru.course.client.models.users.AppUser;
import ru.course.client.services.PurchaseService;

import java.util.List;

@Service
@PropertySource("classpath:properties/app.properties")
@RequiredArgsConstructor
public class RestPurchaseService implements PurchaseService {
    private final OkHttpClient okHttpClient;
    private final Gson gson;
    @Value("${server.url}")
    private String SERVER_URL;


    @Override
    @SneakyThrows
    public void save(Long productId, Long appUserId) {
        String jsonModel = gson.toJson(Purchase.builder()
                .appUser(AppUser.builder().id(appUserId).build())
                .product(Product.builder().id(productId).build()));
        RequestBody requestBody = RequestBody.create(jsonModel, MediaType.parse("application/json; charset=utf-8"));
        okHttpClient.newCall(new Request.Builder()
                .post(requestBody)
                .url(SERVER_URL + "/v1/purchases")
                .build())
                .execute();
    }

    @Override
    @SneakyThrows
    public List<Purchase> findAll() {
        String jsonResponse = okHttpClient.newCall(new Request.Builder()
                .url(SERVER_URL + "/v1/purchases")
                .build())
                .execute()
                .body()
                .string();
        return gson.fromJson(jsonResponse, new TypeToken<List<Purchase>>() {
        }.getType());
    }
}
