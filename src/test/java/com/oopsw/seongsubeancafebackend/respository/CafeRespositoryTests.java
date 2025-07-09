package com.oopsw.seongsubeancafebackend.respository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest //기본 적으로 각 메서마다 롤백함
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
public class CafeRespositoryTests {

  @Autowired
  CafeRepository cafeRepository;

  //메서드 명명법
  //테스트하는 메서드_상황_예상결과

  /*
  추가: 입력데이터가 유효한지
  조회: 조회할 데이터가 존재하는지
  수정: 수정할 데이터가 존재하는지, 입력데이터가 유효한지
  삭제: 삭제할 데이터가 존재하는지

  ValidData <-> InvalidData(NullValue, TooLongValue)
  ExistingId <-> NonExistingId
  */

  @Test
  @Order(1)
//  @Rollback(false)
//  @Commit
  public void saveCafe_ValidData_Success() {
    //given
    CafeEntity cafeEntity = CafeEntity.builder()
        .email("owner@coffeehouse.com")
        .cafeName("발할라")
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    //when
    CafeEntity resultCafeEntity = cafeRepository.save(cafeEntity);

    //then
    assertAll(
        () -> assertThat(resultCafeEntity.getCafeId()).isEqualTo(6L),
        () -> assertThat(resultCafeEntity.getEmail()).isEqualTo("owner@coffeehouse.com"),
        () -> assertThat(resultCafeEntity.getCafeName()).isEqualTo("발할라"),
        () -> assertThat(resultCafeEntity.getZipCode()).isEqualTo("64406"),
        () -> assertThat(resultCafeEntity.getCafeAddress()).isEqualTo("서울특별시 강남구 테헤란로 152"),
        () -> assertThat(resultCafeEntity.getCafeDetailAddress()).isEqualTo("3층 301호"),
        () -> assertThat(resultCafeEntity.getCafeIntroduction()).isEqualTo("조용하고 아늑한 카페입니다"),
        () -> assertThat(resultCafeEntity.getPhoneNumber()).isEqualTo("02-1234-5678"),
        () -> assertThat(resultCafeEntity.getImage()).isEqualTo("/images/cafes/Cafe1.png"),
        () -> assertThat(resultCafeEntity.getIsBusinessDay()).isTrue()
    );
  }

  @Test
  @Order(2)
  public void saveCafe_NullValue_DataIntegrityViolationException() {
    //given
    CafeEntity cafeEntity = CafeEntity.builder()
        .email("owner@coffeehouse.com")
        .cafeName(null)
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    //when&then
    assertThatThrownBy(() -> cafeRepository.save(cafeEntity))
        .isInstanceOf(DataIntegrityViolationException.class);

  }

  @Test
  @Order(3)
  public void saveCafe_TooLongValue_DataIntegrityViolationException() {
    //given
    String tooLongCafeName = "test".repeat(200);

    CafeEntity cafeEntity = CafeEntity.builder()
        .email("owner@coffeehouse.com")
        .cafeName(tooLongCafeName)
        .zipCode("64406")
        .cafeAddress("서울특별시 강남구 테헤란로 152")
        .cafeDetailAddress("3층 301호")
        .cafeIntroduction("조용하고 아늑한 카페입니다")
        .phoneNumber("02-1234-5678")
        .image("/images/cafes/Cafe1.png").build();

    //when&then
    assertThatThrownBy(() -> cafeRepository.save(cafeEntity))
        .isInstanceOf(DataIntegrityViolationException.class);

  }

  @Test
  @Order(4)
  public void findByCafeId_ExistingCafeId_Success() {
    //given
    Long existingCafeId = 1L;

    //when
    CafeEntity resultCafeEntity = cafeRepository.findById(existingCafeId).get();

    //then
    assertThat(resultCafeEntity.getCafeId()).isEqualTo(existingCafeId);
  }

  @Test
  @Order(5)
  public void findByCafeId_NonExistingCafeId_NoSuchElementException() {
    //given
    Long nonExistingCafeId = 999L;

    //when&then
    assertThatThrownBy(() -> {
      CafeEntity cafeEntity = cafeRepository.findById(nonExistingCafeId)
          .orElseThrow(() -> new NoSuchElementException("Cafe not found"));
    }).isInstanceOf(NoSuchElementException.class);

  }

  @Test
  @Order(6)
  public void updateCafe_ExistCafeId_ValidData_Success() {

    //given
    CafeEntity cafeEntity = cafeRepository.findById(1L).get();
    String originCafeName = "단단한 카페";
    cafeEntity.setCafeName(originCafeName);

    //when
    CafeEntity resultCafeEntity = cafeRepository.save(cafeEntity);

    //then
    assertThat(resultCafeEntity.getCafeName()).isEqualTo(originCafeName);
  }

  @Test
  @Order(7)
  public void updateCafe_NonExistingCafeId_NoSuchElementException() {

    // given
    Long nonExistingId = 999L;

    // when & then
    assertThatThrownBy(() -> {
      CafeEntity cafeEntity = cafeRepository.findById(nonExistingId)
          .orElseThrow(() -> new NoSuchElementException("Cafe not found"));
      cafeEntity.setCafeName("수정된 카페명");
      cafeRepository.save(cafeEntity);
    }).isInstanceOf(NoSuchElementException.class);

  }

  @Test
  @Order(8)
  public void updateCafe_NullValue_DataIntegrityViolationException() {

    // given
    Long existingCafeId = 1L;
    CafeEntity cafeEntity = cafeRepository.findById(existingCafeId).get();

    //when & then
    assertThatThrownBy(() -> {
      cafeEntity.setCafeName(null); // nullable = false 위반
      cafeRepository.save(cafeEntity);
      cafeRepository.flush();// 즉시 반영해야지 테스트 가능, 지연쓰기
    }).isInstanceOf(DataIntegrityViolationException.class);
  }

  @Test
  @Order(9)
  public void updateCafe_TooLongValue_DataIntegrityViolationException() {
    //given
    Long existingCafeId = 1L;
    String tooLongCafeName = "test".repeat(200);
    CafeEntity cafeEntity = cafeRepository.findById(existingCafeId).get();

    //when&then
    assertThatThrownBy(() -> {
      cafeEntity.setCafeName(tooLongCafeName);
      cafeRepository.save(cafeEntity);
      cafeRepository.flush(); //즉시 반영해야지 테스트 가능
    }).isInstanceOf(DataIntegrityViolationException.class);
  }

  @Test
  @Order(9)
  public void deleteCafe_ExistingCafeId_Success() {
    //given
    Long existingCafeId = 1L;

    //when
    cafeRepository.deleteById(existingCafeId);

    //then
    assertThat(cafeRepository.existsById(existingCafeId)).isFalse();
  }

  @Test
  @Order(10)
  public void deleteCafe_NonExistCafeId_Success() {
    //given
    Long existingCafeId = 999L;

    //when
    cafeRepository.deleteById(existingCafeId);
  }


}
