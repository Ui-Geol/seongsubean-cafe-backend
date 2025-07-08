package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.exception.CafeCreationException;
import com.oopsw.seongsubeancafebackend.exception.CafeNotFoundException;
import com.oopsw.seongsubeancafebackend.exception.CafeUpdateException;
import com.oopsw.seongsubeancafebackend.exception.InvalidCafeIdException;
import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
    try {
      CafeEntity resultCafeEntity = cafeRepository.save(requestCafeEntity);

      //cafeEntity의 cafeId를 반환
      return resultCafeEntity.getCafeId();
    } catch (Exception e) {
      throw new CafeCreationException("카페 생성에 실패하였습니다");
    }

  }

  @Override
  public CafeDTO getCafe(Long cafeId) {

    validateCafeId(cafeId);

    try {
      //카페 엔티티 불러오기
      CafeEntity resultCafeEntity = cafeRepository.findById(cafeId)
          .orElseThrow(() -> new CafeNotFoundException("카페를 찾을 수 없습니다"));

      //cafeDTO에 결과 매핑
      CafeDTO resultCafeDTO = new ModelMapper().map(resultCafeEntity, CafeDTO.class);

      //cafeDTO 반환
      return resultCafeDTO;
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new CafeNotFoundException("카페 조회에 실패하였습니다");
    }

  }

  @Override
  public Long updateCafe(CafeDTO cafeDTO) {

    validateCafeId(cafeDTO.getCafeId());

    if (!cafeRepository.existsById(cafeDTO.getCafeId())) {
      throw new CafeUpdateException("카페 Id가 존재하지 않습니다");
    }

    try {
      //cafeDTO를 cafeEntity에 매핑
      CafeEntity requestCafeEntity = new ModelMapper().map(cafeDTO, CafeEntity.class);

      //카페를 수정
      CafeEntity resultCafeEntity = cafeRepository.save(requestCafeEntity);

      //cafeId를 반환
      return resultCafeEntity.getCafeId();
    } catch (Exception e) {
      throw new CafeUpdateException("카페 수정에 실패하였습니다");
    }
  }

  // 공통 검증 메서드
  private void validateCafeId(Long cafeId) {
    if (cafeId == null || cafeId <= 0) {
      throw new InvalidCafeIdException("카페 ID는 1 이상이어야 합니다.");
    }
  }
}
