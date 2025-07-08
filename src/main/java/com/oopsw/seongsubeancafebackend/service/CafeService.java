package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.vo.CafeRequest;
import com.oopsw.seongsubeancafebackend.vo.CafeResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface CafeService {

  // 1. 카페 등록 (회원)
  void registerCafe(CafeRequest request);

  // 2. 카페 단건 조회
  CafeResponse getCafeById(Long cafeId);


//  // 11. 카페 수정
//  CafeResponse updateCafe(Long cafeId, CafeRequest request);


  CafeResponse updateCafe(Long cafeId, CafeEntity requestEntity);

  // 12. 카페 삭제
  void deleteCafe(Long cafeId);

}