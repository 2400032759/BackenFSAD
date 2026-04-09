package com.klu.project.service;

import com.klu.project.dto.request.LoginRequestDTO;
import com.klu.project.dto.request.RegisterRequestDTO;
import com.klu.project.dto.response.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);
}
