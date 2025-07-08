package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;

public interface CafeService {
  CafeDTO findByCafeId(Long cafeId);
  CafeDTO addCafe(CafeDTO cafeDTO);
  CafeDTO updateCafe(Long cafeId, CafeDTO cafeDTO);
  Boolean deleteCafe(Long cafeId);
}
