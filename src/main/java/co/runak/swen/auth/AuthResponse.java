package co.runak.swen.auth;

import co.runak.swen.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class AuthResponse {

    private Long id;
    private String name;
    private Set<Role> roles;
}
