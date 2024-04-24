package com.farmer.Order.DTO;

import com.farmer.Order.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDetailDTO {

    @JsonProperty("order_id")
    private UUID orderId;

    @JsonProperty("farm_id")
    private UUID farmId;

    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("completion_time")
    private LocalDateTime completionTime;

    @JsonProperty("status")
    private OrderStatus status;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getFarmId() {
        return farmId;
    }

    public void setFarmId(UUID farmId) {
        this.farmId = farmId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
