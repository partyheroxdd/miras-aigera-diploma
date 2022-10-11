package kz.iitu.miras_aigera_diploma.service.security;

import java.util.HashSet;
import java.util.Set;
import kz.iitu.miras_aigera_diploma.model.entity.Role;
import kz.iitu.miras_aigera_diploma.model.entity.User;
import kz.iitu.miras_aigera_diploma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository
        .findByUsername(username)
        .orElseThrow(
            () -> new UsernameNotFoundException("User not found with username : " + username));

    Set<Role> roles = user.getRoles();

    return org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities(getSimpleGrantedAuthorities(roles))
        .accountExpired(false)
        .accountLocked(false)
        .disabled(false)
        .credentialsExpired(false)
        .build();


  }

  private Set<GrantedAuthority> getSimpleGrantedAuthorities(Set<Role> roles) {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

    for (Role role : roles) {
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return grantedAuthorities;
  }
}
