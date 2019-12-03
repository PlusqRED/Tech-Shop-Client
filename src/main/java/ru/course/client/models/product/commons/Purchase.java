package ru.course.client.models.product.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.course.client.models.product.Product;
import ru.course.client.models.statistic.StatisticItem;
import ru.course.client.models.users.AppUser;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    private AppUser appUser;
    private Product product;
    private String purchaseDate;

    public static StatisticItem toStatisticItem(Purchase purchase) {
        return StatisticItem.builder()
                .dealDate(purchase.purchaseDate)
                .customerName(purchase.appUser.getLogin())
                .dealPrice(String.valueOf(purchase.product.getPrice()))
                .productName(purchase.product.getName())
                .build();
    }
}
