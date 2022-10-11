package kz.iitu.miras_aigera_diploma.security.jwt;


import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.security.ITokenProvider;
import kz.iitu.miras_aigera_diploma.security.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements ITokenProvider {

  @Value("${security.jwt.token.secret-key}")
  private String secretKeyValue;
  @Value("${security.jwt.token.expiration}")
  private long expiration;

  private final IJwtTokenHelper tokenHelper;

  private final UserDetailsService userDetailsService;

  @Override
  public AccessToken createToken(String username, Set<Role> roles) {
    SecretKey secretKey = new SecretKey(secretKeyValue, expiration);
    String token = tokenHelper.generateJwtToken(secretKey, username, roles);
    return new AccessToken(token);
  }

  @Override
  public boolean validateToken(AccessToken accessToken) {
    SecretKey secretKey = new SecretKey(secretKeyValue, expiration);
    return tokenHelper.validateJwtToken(secretKey, accessToken);
  }

  @Override
  public AccessToken getTokenFromHeader(HttpServletRequest httpServletRequest) {
    String bearerToken = httpServletRequest.getHeader("Authorization");
    if (bearerToken == null) {
      return null;
    }
    if (!bearerToken.startsWith("Bearer ")) {
      return null;
    }
    return new AccessToken(bearerToken.substring(7));
  }

  @Override
  public Authentication getAuthentication(AccessToken accessToken) {

    SecretKey secretKey = new SecretKey(secretKeyValue, expiration);
    String username = tokenHelper.getUsernameFromJwtToken(secretKey, accessToken);

    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
        userDetails.getAuthorities());
    return authentication;
  }


}
