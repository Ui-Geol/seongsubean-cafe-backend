package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cafeServiceImpl implements CafeService {

  CafeRepository cafeRepository;

  @Autowired
  public cafeServiceImpl(CafeRepository cafeRepository) {
    this.cafeRepository = cafeRepository;
  }

  @Override
  public Long createCafe(CafeDTO cafeDTO) {
    //cafeDTO를 cafeEntity로 변환
    CafeEntity requestCafeEntity = new ModelMapper().map(cafeDTO, CafeEntity.class);

    //cafeEntity를 저장
    CafeEntity resultCafeEntity = cafeRepository.save(requestCafeEntity);

    //cafeEntity의 cafeId를 반환
    return resultCafeEntity.getCafeId();
  }
}
