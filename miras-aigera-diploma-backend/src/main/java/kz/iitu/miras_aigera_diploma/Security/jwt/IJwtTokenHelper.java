package kz.iitu.miras_aigera_diploma.security.jwt;

import java.util.Set;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.security.AccessToken;
import kz.iitu.miras_aigera_diploma.security.SecretKey;

public interface IJwtTokenHelper {

  String generateJwtToken(SecretKey secretKey, String username, Set<Role> roles);

  boolean validateJwtToken(SecretKey secretKey, AccessToken accessToken);

  String getUsernameFromJwtToken(SecretKey secretKey, AccessToken accessToken);
}
