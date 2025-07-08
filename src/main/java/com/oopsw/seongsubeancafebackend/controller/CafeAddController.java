package com.oopsw.seongsubeancafebackend.controller;

import com.oopsw.seongsubeancafebackend.jpa.CafeEntity;
import com.oopsw.seongsubeancafebackend.service.CafeService;
import com.oopsw.seongsubeancafebackend.vo.CafeRequest;
import com.oopsw.seongsubeancafebackend.vo.CafeResponse;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

public class CafeAddController {

  @RestController
  @RequestMapping("/cafes")
  @RequiredArgsConstructor
  public static class CafeController {

    private final CafeService cafeService;

    @PostMapping
    public ResponseEntity<Void>
    registerCafe(@RequestBody CafeRequest request) {
      cafeService.registerCafe(request);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cafeId}")
    public ResponseEntity<CafeResponse> getCafe(@PathVariable Long cafeId) {
      return ResponseEntity.ok(cafeService.getCafeById(cafeId));
    }
    @PutMapping("/{cafeId}")
    public ResponseEntity<CafeResponse> updateCafe(
        @PathVariable Long cafeId,
        @RequestBody CafeEntity requestEntity) {

      CafeResponse updated = cafeService.updateCafe(cafeId, requestEntity);
      return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{cafeId}")
    public ResponseEntity<Void> deleteCafe(@PathVariable Long cafeId) {
      cafeService.deleteCafe(cafeId);
      return ResponseEntity.noContent().build();
    }


  }
}
