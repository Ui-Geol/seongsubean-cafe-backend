package com.oopsw.seongsubeancafebackend.controller;

import com.oopsw.seongsubeancafebackend.dto.CafeDTO;
import com.oopsw.seongsubeancafebackend.jpa.CafeRepository;
import com.oopsw.seongsubeancafebackend.service.CafeService;
import com.oopsw.seongsubeancafebackend.vo.CafeRequest;
import com.oopsw.seongsubeancafebackend.vo.CafeResponse;
import java.util.Map;
import javax.sound.midi.SysexMessage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cafes")
@RequiredArgsConstructor
public class CafeController {
  public final CafeService cafeService;
  private final CafeRepository cafeRepository;

  @GetMapping("/{cafeId}")
  public ResponseEntity<CafeResponse> getCafeByCafeId(@PathVariable Long cafeId) {
    CafeDTO cafeDTO = cafeService.findByCafeId(cafeId);
    CafeResponse cafeResponse = new ModelMapper().map(cafeDTO, CafeResponse.class);
    return ResponseEntity.ok(cafeResponse);
  }

  @PostMapping
  public ResponseEntity<Map<String, Long>> addCafe(@RequestBody CafeRequest cafeRequest){
    CafeDTO cafeDTO = cafeService.addCafe(new ModelMapper().map(cafeRequest, CafeDTO.class));
    Long cafeId = cafeDTO.getCafeId();
    return ResponseEntity.ok(Map.of("cafeId", cafeId));
  }

  @PutMapping("/{cafeId}")
  public ResponseEntity<Map<String, Long>> updateCafe(@PathVariable Long cafeId, @RequestBody CafeRequest cafeRequest){
    cafeService.updateCafe(cafeId, new ModelMapper().map(cafeRequest, CafeDTO.class));
    return ResponseEntity.ok(Map.of("cafeId", cafeId));
  }

  @DeleteMapping("/{cafeId}")
  public ResponseEntity<Map<String, String>> deleteCafe(@PathVariable Long cafeId) {
    boolean result = cafeService.deleteCafe(cafeId);
    if (result) {
      return ResponseEntity.ok(Map.of("message", "카페가 삭제됨"));
    } else {
      return ResponseEntity.status(404).body(Map.of("message", "해당 카페가 없습니다"));
    }
  }
}
