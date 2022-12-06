package org.zerock.api01.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.api01.util.JWTUtil;

//특정한 경로를 호출할 때 이 토큰들을 검사하고, 문제가 없을때만 접근을 가능하도록 구성

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter { //OncePerRequestFilter는 하나의 요청에 대해서 한번씩 동작하는 필터

  private final JWTUtil jwtUtil;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String path = request.getRequestURI();

    if (!path.startsWith("/api")) {
      filterChain.doFilter(request, response);
      return;
    }

    log.info("Token Check Filter..................");
    log.info("JWTUtil: " + jwtUtil);

    filterChain.doFilter(request, response);
  }
}
