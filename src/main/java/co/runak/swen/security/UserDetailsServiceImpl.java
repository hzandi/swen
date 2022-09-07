package co.runak.swen.security;

import co.runak.swen.user.AppUser;
import co.runak.swen.user.Role;
import co.runak.swen.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser appUser = userService.getByUsername(username);
        List<SimpleGrantedAuthority> authorities = appUser.getRoles().stream()
                .map(this::mapRoleToSimpleGrantedAuthority).toList();
        return mapUserToCustomUserDetails(appUser, authorities);
    }

    private SimpleGrantedAuthority mapRoleToSimpleGrantedAuthority(Role role){
        return new SimpleGrantedAuthority(role.name());
    }

    private CustomUserDetails mapUserToCustomUserDetails(AppUser appUser, List<SimpleGrantedAuthority> authorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setId(appUser.getId());
        customUserDetails.setUsername(appUser.getUsername());
        customUserDetails.setPassword(appUser.getPassword());
        customUserDetails.setName(appUser.getName());
        customUserDetails.setEmail(appUser.getEmail());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }
}
