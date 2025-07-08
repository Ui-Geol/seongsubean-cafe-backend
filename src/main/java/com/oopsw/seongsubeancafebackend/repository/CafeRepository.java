package com.oopsw.seongsubeancafebackend.repository;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<CafeEntity, Long> {

}
