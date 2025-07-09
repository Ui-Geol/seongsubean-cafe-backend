package com.oopsw.seongsubeancafebackend.service;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import java.time.LocalDateTime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;

    public CafeServiceImpl(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    public CafeDTO addCafe(CafeDTO cafeDTO) {
        CafeEntity cafeEntity = cafeRepository.save(new ModelMapper().map(cafeDTO, CafeEntity.class));
        return new ModelMapper().map(cafeEntity, CafeDTO.class);
    }


    public CafeDTO findByCafeId(Long cafeId) {
        CafeEntity cafeEntity = cafeRepository.findByCafeId(cafeId);
        CafeDTO cafeDTO = new ModelMapper().map(cafeEntity, CafeDTO.class);
        return cafeDTO;
    }

    public CafeDTO updateCafe(Long cafeId, CafeDTO cafeDTO) {
        CafeEntity existingEntity = cafeRepository.findByCafeId(cafeId);
        if (existingEntity == null) {
            throw new IllegalArgumentException("Cafe not found with id: " + cafeId);
        }
        existingEntity.setCafeName(cafeDTO.getCafeName());
        existingEntity.setCafeAddress(cafeDTO.getCafeAddress());
        existingEntity.setPhoneNumber(cafeDTO.getPhoneNumber());
        existingEntity.setCafeIntroduction(cafeDTO.getCafeIntroduction());
        existingEntity.setUpdatedAt(LocalDateTime.now());
        CafeEntity updated = cafeRepository.save(existingEntity);
        return new ModelMapper().map(updated, CafeDTO.class);
    }

    public void deleteCafe(Long cafeId) {
        CafeEntity existingEntity = cafeRepository.findByCafeId(cafeId);
        if (existingEntity == null) {
            throw new IllegalArgumentException("Cafe not found with id: " + cafeId);
        }
        cafeRepository.delete(existingEntity);
    }

}
