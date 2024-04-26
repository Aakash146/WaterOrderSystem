package com.farmer.Order.Service;

import com.farmer.Order.Controller.AuthenticationRequest;
import com.farmer.Order.Controller.AuthenticationResponse;
import com.farmer.Order.Controller.RegiserRequest;
import com.farmer.Order.DTO.FarmerDTO;
import com.farmer.Order.Enum.Role;
import com.farmer.Order.Repository.FarmerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final FarmerService farmerService;
    private final FarmerRepository farmerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegiserRequest request) {

        var farmerDTO = FarmerDTO.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .role(Role.ADMIN)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        var farmer = farmerService.addNewFarmer(farmerDTO);
        var jwtToken = jwtService.generateToken(farmer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var farmer = farmerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Farmer not found."));

        return AuthenticationResponse.builder()
                .token(jwtService.generateToken(farmer))
                .build();
    }
}
