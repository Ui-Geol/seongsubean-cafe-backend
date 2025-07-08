package com.oopsw.seongsubeancafebackend.service;


import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {
  public final CafeRepository cafeRepository;

  @Override
  public CafeDTO findByCafeId(Long cafeId) {
    CafeEntity cafeEntity = cafeRepository.findByCafeId(cafeId);
    if(cafeEntity == null){
      throw new NullPointerException("찾으시는 카페가 없습니다.");
    }
    CafeDTO cafeDTO = new ModelMapper().map(cafeEntity, CafeDTO.class);
    return cafeDTO;
  }

  @Override
  public CafeDTO addCafe(CafeDTO cafeDTO) {
    CafeEntity cafeEntity = cafeRepository.save(new ModelMapper().map(cafeDTO, CafeEntity.class));
    CafeDTO cafe = new ModelMapper().map(cafeEntity, CafeDTO.class);
    return cafe;
  }

  @Override
  public CafeDTO updateCafe(Long cafeId, CafeDTO cafeDTO) {
    CafeEntity cafeEntity = cafeRepository.save(new ModelMapper().map(CafeDTO.builder()
        .cafeId(cafeId)
        .cafeName(cafeDTO.getCafeName())
        .cafeAddress(cafeDTO.getCafeAddress())
        .cafeDetailAddress(cafeDTO.getCafeDetailAddress())
        .zipCode(cafeDTO.getZipCode())
        .phoneNumber(cafeDTO.getPhoneNumber())
        .cafeIntroduction(cafeDTO.getCafeIntroduction())
        .image(cafeDTO.getImage())
        .email(cafeDTO.getEmail())
        .status(cafeDTO.getStatus())
        .updatedAt(LocalDateTime.now())
        .build(), CafeEntity.class));
    CafeDTO cafe = new ModelMapper().map(cafeEntity, CafeDTO.class);
    return cafe;
  }

  @Override
  @Transactional
  public Boolean deleteCafe(Long cafeId) {
    return cafeRepository.deleteByCafeId(cafeId) > 0;
  }
}
