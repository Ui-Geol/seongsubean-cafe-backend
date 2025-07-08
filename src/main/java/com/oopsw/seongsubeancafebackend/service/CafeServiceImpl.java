package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.repository.CafeRepository;
import com.oopsw.seongsubeancafebackend.vo.CafeRequest;
import com.oopsw.seongsubeancafebackend.vo.CafeResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {

  CafeRepository cafeRepository;

  @Autowired
  public CafeServiceImpl(CafeRepository cafeRepository) {
    this.cafeRepository = cafeRepository;
  }


  @Override
  public void registerCafe(CafeRequest request) {
    CafeEntity cafe = new CafeEntity();

    cafe.setCafeId(request.getCafeId());
    cafe.setCafeName(request.getCafeName());
    cafe.setEmail(request.getEmail());
    cafe.setZipCode(request.getZipCode());
    cafe.setImage(request.getImage());
    cafe.setCafeAddress(request.getCafeAddress());
    cafe.setPhoneNumber(request.getPhoneNumber());
    cafe.setCafeIntroduction(request.getCafeIntroduction());

    // 상태는 신규 등록 시 기본값 true 또는 false로 설정 가능
    cafe.setStatus(false); // 예: 대기 상태로 false 설정

    // 저장
    cafeRepository.save(cafe);
  }


  @Override
  public CafeResponse getCafeById(Long cafeId) {
    List<CafeEntity> cafeEntities = cafeRepository.findAll();

    for (CafeEntity cafeEntity : cafeEntities) {
      if (cafeEntity.getCafeId().equals(cafeId)) {
        CafeResponse cafe = new CafeResponse();

        cafe.setCafeId(cafeEntity.getCafeId());
        cafe.setEmail(cafeEntity.getEmail());
        cafe.setCafeName(cafeEntity.getCafeName());
        cafe.setZipCode(cafeEntity.getZipCode());
        cafe.setCafeAddress(cafeEntity.getCafeAddress());
        cafe.setPhoneNumber(cafeEntity.getPhoneNumber());
        cafe.setCafeIntroduction(cafeEntity.getCafeIntroduction());
        cafe.setImage(cafeEntity.getImage());

        return cafe;  // ✅ 단일 객체 반환
      }
    }

    throw new RuntimeException("해당 카페를 찾을 수 없습니다.");
  }

// CafeServiceImpl.java 내부

  @Transactional
  @Override
  public CafeResponse updateCafe(Long cafeId, CafeEntity requestEntity) {
    CafeEntity cafe = cafeRepository.findById(cafeId)
        .orElseThrow(() -> new RuntimeException("해당 카페가 존재하지 않습니다."));

    // 수정 (Entity 기준으로 필드 대입)
    cafe.setCafeName(requestEntity.getCafeName());
    cafe.setEmail(requestEntity.getEmail());
    cafe.setZipCode(requestEntity.getZipCode());
    cafe.setCafeAddress(requestEntity.getCafeAddress());
    cafe.setCafeDetailAddress(requestEntity.getCafeDetailAddress());
    cafe.setPhoneNumber(requestEntity.getPhoneNumber());
    cafe.setCafeIntroduction(requestEntity.getCafeIntroduction());
    cafe.setImage(requestEntity.getImage());

    // 저장 (JPA가 변경 감지 → UPDATE 실행)
    CafeEntity saved = cafeRepository.save(cafe);

    // 응답용 객체 생성 (Response DTO)
    CafeResponse response = new CafeResponse();
    response.setCafeId(saved.getCafeId());
    response.setCafeName(saved.getCafeName());
    response.setEmail(saved.getEmail());
    response.setZipCode(saved.getZipCode());
    response.setCafeAddress(saved.getCafeAddress());
    response.setCafeDetailAddress(saved.getCafeDetailAddress());
    response.setPhoneNumber(saved.getPhoneNumber());
    response.setCafeIntroduction(saved.getCafeIntroduction());
    response.setImage(saved.getImage());

    return response;
  }






  @Override
  @Transactional
  public void deleteCafe(Long cafeId) {
    CafeEntity cafe = cafeRepository.findById(cafeId)
        .orElseThrow(() -> new RuntimeException("해당 카페가 존재하지 않습니다."));

    cafeRepository.delete(cafe);
  }


}


