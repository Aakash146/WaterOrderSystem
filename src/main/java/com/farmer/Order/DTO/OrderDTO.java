package com.farmer.Order.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDTO {

    @JsonProperty("farm_id")
    private UUID farmId;

    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    @JsonProperty("duration")
    private Integer duration;

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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "farmId=" + farmId +
                ", startDateTime=" + startDateTime +
                ", duration=" + duration +
                '}';
    }
}
