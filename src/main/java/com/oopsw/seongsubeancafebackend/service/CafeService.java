package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;

public interface CafeService {
    CafeDTO addCafe(CafeDTO cafeDTO);
    CafeDTO findByCafeId(Long cafeId);
    CafeDTO updateCafe(Long cafeId, CafeDTO cafeDTO);
    void deleteCafe(Long cafeId);
}
