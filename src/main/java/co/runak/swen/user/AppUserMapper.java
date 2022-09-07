package co.runak.swen.user;

import org.mapstruct.Mapper;
import java.util.List;


@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser toAppUser(AppUserDTO appUserDTO);
    AppUserDTO toAppUserDTO(AppUser appUser);
    List<AppUser> toAppUserList(List<AppUserDTO> appUserDTOS);
    List<AppUserDTO> toAppUserDTOList(List<AppUser> appUserList);
}
