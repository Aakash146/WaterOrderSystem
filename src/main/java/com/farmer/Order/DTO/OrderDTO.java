package com.farmer.Order.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OrderDTO {

    @JsonProperty("farmer_id")
    private UUID farmId;

    @JsonProperty("start_date_time")
    private LocalDateTime startDateTime;

    @JsonProperty("duration")
    private Integer duration;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "farmId=" + farmId +
                ", startDateTime=" + startDateTime +
                ", duration=" + duration +
                '}';
    }
}
