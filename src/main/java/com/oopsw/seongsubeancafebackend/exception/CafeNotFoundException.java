package com.oopsw.seongsubeancafebackend.exception;

//cafe를 찾을 수 없는 사용자 정의 예외
//EntityNotFoundException은 너무 포괄적임
public class CafeNotFoundException extends RuntimeException {

  public CafeNotFoundException(String message) {
    super(message);
  }

}
