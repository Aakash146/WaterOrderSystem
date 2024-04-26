package com.farmer.Order.Service;

import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.DTO.FarmerDetailDTO;
import com.farmer.Order.Entity.Farmer;

import java.util.List;
import java.util.UUID;

public interface IFarmerService {

    List<FarmerDetailDTO> getAllFarmerDetail();

    Farmer addNewFarmer(FarmerDTO farmerDTO);

    String deleteFarmer(UUID farmId);
}
