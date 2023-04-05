package kz.iitu.miras_aigera_diploma.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import kz.iitu.miras_aigera_diploma.exceptions.DiplomaCoreException;
import kz.iitu.miras_aigera_diploma.model.constants.ApiMessages;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.security.SecretKey;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtsTokenHelper implements IJwtTokenHelper {

  @Override
  public String generateJwtToken(SecretKey secretKey, String username, Set<Role> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("authorities", roles.stream().map(role -> {
      return new SimpleGrantedAuthority(role.getName());
    }).collect(Collectors.toList()));

    Date issuedAt = new Date();
    Date validUntil = new Date(issuedAt.getTime() + secretKey.getExpirationInMiliseconds());
    String secretKeyEncoded = Base64.getEncoder()
        .encodeToString(secretKey.getSecretKey().getBytes());
    return Jwts
        .builder()
        .setClaims(claims)
        .setIssuedAt(issuedAt)
        .setExpiration(validUntil)
        .signWith(SignatureAlgorithm.HS256, secretKeyEncoded)
        .compact();
  }

  @Override
  public boolean validateJwtToken(SecretKey secretKey, AccessToken accessToken) {
    try {
      String secretKeyEncoded = Base64.getEncoder()
          .encodeToString(secretKey.getSecretKey().getBytes());
      Jwts.parser().setSigningKey(secretKeyEncoded).parseClaimsJws(accessToken.getToken());
      return true;
    } catch (JwtException | IllegalArgumentException exception) {
      throw new DiplomaCoreException(HttpStatus.UNAUTHORIZED, ApiMessages.INVALID_TOKEN,
          "Expired or Invalid Token");
    }
  }

  @Override
  public String getUsernameFromJwtToken(SecretKey secretKey, AccessToken accessToken) {
    String secretKeyEncoded = Base64.getEncoder()
        .encodeToString(secretKey.getSecretKey().getBytes());
    return Jwts.parser().setSigningKey(secretKeyEncoded).parseClaimsJws(accessToken.getToken())
        .getBody().getSubject();
  }

}
