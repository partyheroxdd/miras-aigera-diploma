package kz.iitu.miras_aigera_diploma.Security.jwt;

import java.util.Set;
import kz.iitu.miras_aigera_diploma.Security.AccessToken;
import kz.iitu.miras_aigera_diploma.Security.SecretKey;
import kz.iitu.miras_aigera_diploma.model.entity.Role;

public interface IJwtTokenHelper {

  String generateJwtToken(SecretKey secretKey, String username, Set<Role> roles);

  boolean validateJwtToken(SecretKey secretKey, AccessToken accessToken);

  String getUsernameFromJwtToken(SecretKey secretKey, AccessToken accessToken);
}
