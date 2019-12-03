package ru.course.client.models.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticItem {
    private String dealDate;
    private String dealPrice;
    private String customerName;
    private String productName;
}
