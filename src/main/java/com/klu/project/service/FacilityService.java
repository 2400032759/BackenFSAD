package com.klu.project.service;

import com.klu.project.dto.response.HostelResponseDTO;
import com.klu.project.dto.response.TransportResponseDTO;

public interface FacilityService {

    HostelResponseDTO getHostelInfo(String username);

    TransportResponseDTO getTransportInfo(String username);
}
