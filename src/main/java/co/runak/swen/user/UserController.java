package co.runak.swen.user;

import co.runak.swen.common.PagingData;
import co.runak.swen.common.SearchCriteria;
import co.runak.swen.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private AppUserMapper mapper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/me")
    public ResponseEntity<AppUserDTO> getUser(@AuthenticationPrincipal CustomUserDetails currentUser) {
        AppUser appUser = service.getByUsername(currentUser.getUsername());
        AppUserDTO appUserDTO = mapper.toAppUserDTO(appUser);
        return ResponseEntity.ok(appUserDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUser(@PathVariable Long id) {
        AppUser appUser = service.getById(id);
        AppUserDTO appUserDTO = mapper.toAppUserDTO(appUser);
        return ResponseEntity.ok(appUserDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/")
    public ResponseEntity saveUser(@RequestBody AppUserDTO appUserDTO){
        AppUser appUser = mapper.toAppUser(appUserDTO);
        service.save(appUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/")
    public ResponseEntity updateUser(@RequestBody AppUserDTO appUserDTO) {
        AppUser appUser = mapper.toAppUser(appUserDTO);
        service.update(appUser);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<AppUserDTO>> getAll() {
        List<AppUserDTO> appUserDTOS = service.getAll().stream().map(mapper::toAppUserDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(appUserDTOS);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/paging/{page}/{size}")
    public ResponseEntity<PagingData<AppUserDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {

        Page<AppUser> appUserPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, appUserPage);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/search")
    public ResponseEntity<List<AppUserDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<AppUser> appUserList = service.search(searchCriteria);
        List<AppUserDTO> appUserDTOS = mapper.toAppUserDTOList(appUserList);
        return ResponseEntity.ok(appUserDTOS);
    }

    private ResponseEntity<PagingData<AppUserDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<AppUser> appUserPage) {
        int totalPage = appUserPage.getTotalPages();
        List<AppUser> data = appUserPage.getContent();
        List<AppUserDTO> appUserDTOS = mapper.toAppUserDTOList(data);

        PagingData<AppUserDTO> pagingData = new PagingData<>(totalPage, page, appUserDTOS);
        return ResponseEntity.ok(pagingData);
    }
}



