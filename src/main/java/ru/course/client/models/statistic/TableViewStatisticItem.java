package ru.course.client.models.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class TableViewStatisticItem {


    private String dealDate;
    private String dealPrice;

    private String customerName;

    private String productName;
}
