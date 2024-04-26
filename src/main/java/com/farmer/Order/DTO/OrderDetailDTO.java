package com.farmer.Order.DTO;

import com.farmer.Order.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDetailDTO {

    @JsonProperty("order_id")
    private UUID orderId;

    @JsonProperty("farmer_id")
    private UUID farmId;

    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("completion_time")
    private LocalDateTime completionTime;

    @JsonProperty("status")
    private OrderStatus status;

}
