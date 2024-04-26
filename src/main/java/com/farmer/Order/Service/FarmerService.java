package com.farmer.Order.Service;

import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.DTO.FarmerDetailDTO;
import com.farmer.Order.Entity.Farmer;
import com.farmer.Order.Exception.ApiRequestException;
import com.farmer.Order.Repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@RequiredArgsConstructor
public class FarmerService implements IFarmerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FarmerService.class);

    @Autowired
    private FarmerRepository farmerRepository;


    @Override
    @Transactional
    public Farmer addNewFarmer(FarmerDTO farmerDTO) {
        final Optional<Farmer> farmer = farmerRepository.findByEmail(farmerDTO.getEmail());
        if (farmer.isPresent()) {
            LOGGER.error("Farmer with email " + farmerDTO.getEmail() + " already exists.");
            throw new ApiRequestException("Email already Exist");
        }
        final Farmer newFarmer = new Farmer();
        newFarmer.setFirstName(farmerDTO.getFirstName());
        newFarmer.setLastName(farmerDTO.getLastName());
        newFarmer.setEmail(farmerDTO.getEmail());
        newFarmer.setRole(farmerDTO.getRole());
        newFarmer.setPassword(farmerDTO.getPassword());
        LOGGER.info("Farmer with email "+ farmerDTO.getEmail() + " added.");
        farmerRepository.save(newFarmer);
        Optional<Farmer> savedFarmer = farmerRepository.findByEmail(farmerDTO.getEmail());
        return savedFarmer.orElseThrow(() -> new UsernameNotFoundException("Farmer not found."));
    }

    @Override
    @Transactional
    public String deleteFarmer(UUID farmId){
        boolean exists = farmerRepository.existsById(farmId);
        if(!exists) {
            LOGGER.error("Farmer with id " + farmId + " does not exist");
            throw new ApiRequestException("Farmer with id " + farmId + " does not exist");
        }
        farmerRepository.deleteById(farmId);
        return "Successfully Deleted.";
    }

    @Override
    @Transactional
    public List<FarmerDetailDTO> getAllFarmerDetail() {
        final List<Farmer> farmers = farmerRepository.findAll();
        final List<FarmerDetailDTO> dtos = new ArrayList<>();
        farmers.forEach(farmer -> {
            final FarmerDetailDTO dto = new FarmerDetailDTO();
            dto.setFarmId(farmer.getFarmId());
            dto.setEmail(farmer.getEmail());
            dto.setFirstName(farmer.getFirstName());
            dto.setLastName(farmer.getLastName());
            dto.setFullName(farmer.getFullName());
            dtos.add(dto);
        });
        return dtos;
    }

}
