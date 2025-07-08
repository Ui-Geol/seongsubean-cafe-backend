package com.oopsw.seongsubeancafebackend.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<CafeEntity, Long> {
  CafeEntity findByCafeId(Long cafeId);
  long deleteByCafeId(Long cafeId);
}
