package ru.course.client.models.product.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.course.client.models.product.Product;
import ru.course.client.models.statistic.TableViewStatisticItem;
import ru.course.client.models.users.AppUser;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class Purchase {
    private AppUser appUser;

    private Product product;
    private String purchaseDate;
    @Override
    public String toString() {
        return "Purchase: " +
                "appUser=" + appUser +
                ", product=" + product +
                ", purchaseDate='" + purchaseDate + '\'';
    }

    public static TableViewStatisticItem toStatisticItem(Purchase purchase) {
        return TableViewStatisticItem.builder()
                .dealDate(purchase.purchaseDate)
                .customerName(purchase.appUser.getLogin())
                .dealPrice(String.valueOf(purchase.product.getPrice()))
                .productName(purchase.product.getName())
                .build();
    }



}
