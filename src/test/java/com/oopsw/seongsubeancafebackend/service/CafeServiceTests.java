package com.oopsw.seongsubeancafebackend.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.exception.CafeCreationException;
import com.oopsw.seongsubeancafebackend.exception.CafeNotFoundException;
import com.oopsw.seongsubeancafebackend.exception.InvalidCafeIdException;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
@Transactional
public class CafeServiceTests {

  @Autowired
  private CafeService cafeService;
  @Autowired
  private EntityManager entityManager;

  @Test
  @Order(1)
  public void createCafe_ValidData_Success() {

    //given
    CafeDTO cafeDTO = CafeDTO.builder()
        .email("owner@coffeehouse.com")
        .cafeName("발할라")
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    //when
    Long resultCafeId = cafeService.createCafe(cafeDTO);

    //then
    Assertions.assertThat(resultCafeId).isEqualTo(6L);

  }

  @Test
  @Order(2)
  public void createCafe_NullValue_CafeCreationException() {

    //given
    String cafeName = null;
    CafeDTO cafeDTO = CafeDTO.builder()
        .email("owner@coffeehouse.com")
        .cafeName(cafeName)
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    //when&then
    assertThatThrownBy(() -> cafeService.createCafe(cafeDTO))
        .isInstanceOf(CafeCreationException.class);

  }

  @Test
  @Order(3)
  public void getCafe_ExistingCafeId_Success() {
    //given
    Long existingCafeId = 1L;

    //when
    CafeDTO resultCafeDTO = cafeService.getCafe(existingCafeId);

    //then
    assertAll(
        () -> assertThat(resultCafeDTO.getEmail()).isEqualTo("owner@bluemoon.com"),
        () -> assertThat(resultCafeDTO.getCafeName()).isEqualTo("블루문 카페"),
        () -> assertThat(resultCafeDTO.getZipCode()).isEqualTo("06292"),
        () -> assertThat(resultCafeDTO.getCafeAddress()).isEqualTo("서울특별시 강남구 테헤란로 152"),
        () -> assertThat(resultCafeDTO.getCafeDetailAddress()).isEqualTo("3층 301호"),
        () -> assertThat(resultCafeDTO.getCafeIntroduction()).isEqualTo(
            "조용하고 아늑한 분위기에서 신선한 원두로 내린 커피를 즐길 수 있는 카페입니다. 무료 와이파이와 콘센트가 구비되어 있어 업무나 스터디하기에도 좋습니다."),
        () -> assertThat(resultCafeDTO.getPhoneNumber()).isEqualTo("02-1234-5678"),
        () -> assertThat(resultCafeDTO.getImage()).isEqualTo("/images/cafes/bluemoon-cafe.jpg"),
        () -> assertThat(resultCafeDTO.getIsBusinessDay()).isTrue()
    );
  }

  @Test
  @Order(4)
  public void getCafe_NonExistingCafeId_CafeNotFoundException() {
    //given
    Long nonExistingCafeId = 999L;

    //when&then
    assertThatThrownBy(() -> cafeService.getCafe(nonExistingCafeId))
        .isInstanceOf(CafeNotFoundException.class);
  }

  @Test
  @Order(5)
  public void getCafe_NullCafeId_CafeNotFoundException() {
    //given
    Long nonExistingCafeId = null;

    //when&then
    assertThatThrownBy(() -> cafeService.getCafe(nonExistingCafeId))
        .isInstanceOf(InvalidCafeIdException.class);
  }

  @Test
  public void updateCafe_ExistingCafeId_ValidData_Success() {
    //given
    CafeDTO cafeDTO = CafeDTO.builder()
        .cafeId(1L)
        .email("owner@coffeehouse.com")
        .cafeName("단단한 카페")
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    //when
    Long resultCafeId = cafeService.updateCafe(cafeDTO);

    //then
    assertThat(resultCafeId).isEqualTo(1L);

  }

  @Test
  public void updateCafe_NonExistingCafeId_CafeNotFoundException() {
    //given
    Long nonExistingCafeId = 999L;
    CafeDTO cafeDTO = CafeDTO.builder()
        .cafeId(nonExistingCafeId)
        .email("owner@coffeehouse.com")
        .cafeName("단단한 카페")
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    //when & then
    assertThatThrownBy(() -> {
      cafeService.updateCafe(cafeDTO);
      entityManager.flush();
    }).isInstanceOf(CafeNotFoundException.class);

  }


  @Test
  void getCafe_NullCafeId_InvalidCafeIdException() {
    // when & then
    assertThatThrownBy(() -> cafeService.getCafe(null))
        .isInstanceOf(InvalidCafeIdException.class);
  }

  @Test
  void getCafe_NegativeCafeId_InvalidCafeIdException() {
    // given
    Long negativeCafeId = -1L;

    // when & then
    assertThatThrownBy(() -> cafeService.getCafe(negativeCafeId))
        .isInstanceOf(InvalidCafeIdException.class);
  }

  @Test
  void getCafe_ZeroCafeId_InvalidCafeIdException() {
    // given
    Long zeroCafeId = 0L;

    // when & then
    assertThatThrownBy(() -> cafeService.getCafe(zeroCafeId))
        .isInstanceOf(InvalidCafeIdException.class);
  }
}
