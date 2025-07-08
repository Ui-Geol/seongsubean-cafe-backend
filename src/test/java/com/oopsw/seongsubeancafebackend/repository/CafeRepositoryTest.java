package com.oopsw.seongsubeancafebackend.repository;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class CafeRepositoryTest {
  private final CafeRepository cafeRepository;

  @Test
  public void successFindByCafeId() {
    CafeEntity cafeEntity = cafeRepository.findByCafeId(1L);
  }
}
