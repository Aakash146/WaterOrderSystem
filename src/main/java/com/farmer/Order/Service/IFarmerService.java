package com.farmer.Order.Service;

import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.DTO.FarmerDetailDTO;

import java.util.List;

public interface IFarmerService {

    List<FarmerDetailDTO> getAllFarmerDetail();
    String addNewFarmer(FarmerDTO farmerDTO);

    String deleteFarmer(Long farmId);
}
