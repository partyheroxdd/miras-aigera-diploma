package kz.iitu.miras_aigera_diploma.Security;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import org.springframework.security.core.Authentication;

public interface ITokenProvider {


  AccessToken createToken(String username, Set<Role> roles);

  boolean validateToken(AccessToken accessToken);

  AccessToken getTokenFromHeader(HttpServletRequest httpServletRequest);

  Authentication getAuthentication(AccessToken accessToken);
}
