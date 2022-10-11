package kz.iitu.miras_aigera_diploma.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kz.iitu.miras_aigera_diploma.exceptions.security.CustomSecurityException;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.security.ITokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

  private final ITokenProvider tokenProvider;

  @Override

  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    AccessToken accessToken = tokenProvider.getTokenFromHeader(httpServletRequest);
    try {
      if (checkAccessToken(accessToken)) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      filterChain.doFilter(httpServletRequest, httpServletResponse);
    } catch (CustomSecurityException customSecurityException) {
      SecurityContextHolder.clearContext();
      throw customSecurityException;
    }

  }

  private boolean checkAccessToken(AccessToken accessToken) {
    if (accessToken == null) {
      return false;
    }
    return tokenProvider.validateToken(accessToken);
  }
}
