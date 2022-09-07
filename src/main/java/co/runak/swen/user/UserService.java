package co.runak.swen.user;

import co.runak.swen.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    AppUser save(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(AppUser appUser);
    void delete(Long id);
    AppUser getById(Long id);
    AppUser getByName(String name);
    AppUser getByUsername(String username);
    boolean hasUserWithUsername(String username);
    boolean hasUserWithEmail(String email);
    AppUser getByEmail(String email);
    List<AppUser> getAll();
    Page<AppUser> paging(Integer page, Integer size);
    List<AppUser> search(List<SearchCriteria> searchCriteria);
}
