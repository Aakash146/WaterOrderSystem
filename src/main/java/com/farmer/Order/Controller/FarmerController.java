package com.farmer.Order.Controller;

import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.DTO.FarmerDetailDTO;
import com.farmer.Order.Service.IFarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/farm")
public class FarmerController {

    @Autowired
    private IFarmerService farmerService;

    @GetMapping
    public List<FarmerDetailDTO> getAllFarmerDetails(){

        return farmerService.getAllFarmerDetail();
    }

    @PostMapping
    public String registerNewFarmer(@RequestBody FarmerDTO farmerDTO){

        return farmerService.addNewFarmer(farmerDTO);
    }

    @DeleteMapping(path = "{farmId}")
    public String deleteFarmer(@PathVariable("farmId") Long farmId){

        return farmerService.deleteFarmer(farmId);
    }
}