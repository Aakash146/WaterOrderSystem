package com.farmer.Order.Controller;

import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.DTO.FarmerDetailDTO;
import com.farmer.Order.Entity.Farmer;
import com.farmer.Order.Enum.Role;
import com.farmer.Order.Service.IFarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/farm")
public class FarmerController {

    @Autowired
    private IFarmerService farmerService;

    @GetMapping
    public ResponseEntity<List<FarmerDetailDTO>> getAllFarmerDetails(){

        return ResponseEntity.ok(farmerService.getAllFarmerDetail());
    }

    @PostMapping
    public ResponseEntity<Farmer> registerNewFarmer(@RequestBody FarmerDTO farmerDTO){

        farmerDTO.setRole(Role.USER);
        return ResponseEntity.ok(farmerService.addNewFarmer(farmerDTO));
    }

    @DeleteMapping(path = "{farmId}")
    public String deleteFarmer(@PathVariable("farmId") UUID farmId){

        return farmerService.deleteFarmer(farmId);
    }
}