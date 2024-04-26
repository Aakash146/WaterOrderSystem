package com.farmer.Order.DTO;

import com.farmer.Order.Enum.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class FarmerDetailDTO {

    @JsonProperty("farmer_id")
    private UUID farmId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("full_name")
    private String fullName;

}
