package org.zerock.api01.util;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class JWTUtilTests {

  @Autowired
  private JWTUtil jwtUtil;

  //생성 테스트
  @Test
  public void testGenerate(){
    Map<String, Object> claimMap = Map.of("mid", "ABCDE");

    String jwtStr = jwtUtil.generateToken(claimMap, 1);

    log.info(jwtStr);
  }

  //유효 검사
  @Test
  public void testValidate(){
    //유효시간이 지난 토큰
    String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzAzMzAxMzcsIm1pZCI6IkFCQ0RFIiwiaWF0IjoxNjcwMzMwMDc3fQ.syOnjMsOzGJZU6DKKYUDyFxwTkVddho3cnIjTxwc1gc";

    Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

    log.info(claim);
  }

  //생성+검증
  @Test
  public void testAll(){
    String jwtStr = jwtUtil.generateToken(Map.of("mid","AAAA","email","aaaa@bbb.com"), 1);
    log.info(jwtStr);

    Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

    log.info("MID: " + claim.get("mid"));
    log.info("EMAIL : " + claim.get("email"));

  }

  
}
