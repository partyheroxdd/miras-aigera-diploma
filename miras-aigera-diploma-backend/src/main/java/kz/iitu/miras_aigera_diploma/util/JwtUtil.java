package kz.iitu.miras_aigera_diploma.util;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class JwtUtil {

  public String getUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.nonNull(authentication)) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      return userDetails.getUsername();
    }
    return null;
  }

  public String getRole() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (Objects.nonNull(authentication)) {
      Set<String> roles = authentication.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
      return roles.stream().findFirst().get();
    }
    return null;
  }
}
