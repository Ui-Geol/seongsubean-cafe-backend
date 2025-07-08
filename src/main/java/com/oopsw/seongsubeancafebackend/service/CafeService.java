package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.vo.CafeRequest;
import com.oopsw.seongsubeancafebackend.vo.CafeResponse;

public interface CafeService {

  // 1. 카페 등록 (회원)
  void registerCafe(CafeRequest request);

  // 2. 카페 단건 조회
  CafeResponse getCafeById(Long cafeId);

  // 3. 카페수정
  CafeResponse updateCafe(Long cafeId, CafeEntity requestEntity);

  // 4. 카페 삭제
  void deleteCafe(Long cafeId);

}