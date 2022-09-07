package co.runak.swen.auth;

import co.runak.swen.common.exception.BadRequestException;
import co.runak.swen.common.exception.ConflictException;
import co.runak.swen.common.exception.NotFoundException;
import co.runak.swen.user.AppUser;
import co.runak.swen.user.Role;
import co.runak.swen.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AppUser appUser = userService.getByUsername(loginRequest.getUsername());
        if(passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            return ResponseEntity.ok(new AuthResponse(appUser.getId(), appUser.getName(), appUser.getRoles()));
        }
        throw new BadRequestException("Wrong Password Error!");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.hasUserWithUsername(signUpRequest.getUsername())) {
            throw new ConflictException(String.format("Username %s is already been used", signUpRequest.getUsername()));
        }
        if (userService.hasUserWithEmail(signUpRequest.getEmail())) {
            throw new ConflictException(String.format("Email %s is already been used", signUpRequest.getEmail()));
        }

        AppUser appUser = userService.save(createUser(signUpRequest));
        return new AuthResponse(appUser.getId(), appUser.getName(), appUser.getRoles());
    }

    private AppUser createUser(SignUpRequest signUpRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(signUpRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        appUser.setName(signUpRequest.getName());
        appUser.setEmail(signUpRequest.getEmail());
        appUser.setRoles(Set.of(Role.USER));
        return appUser;
    }
}
