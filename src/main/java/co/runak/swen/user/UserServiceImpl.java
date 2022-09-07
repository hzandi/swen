package co.runak.swen.user;

import co.runak.swen.common.SearchCriteria;
import co.runak.swen.common.SearchSpecification;
import co.runak.swen.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", allEntries = true),
    })
    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", allEntries = true),
    })
    public AppUser update(AppUser appUser) {
        AppUser lastSavedAppUser = getById(appUser.getId());
        lastSavedAppUser.setName(appUser.getName());
        lastSavedAppUser.setUsername(appUser.getUsername());
        lastSavedAppUser.setEmail(appUser.getEmail());
        lastSavedAppUser.setRoles(appUser.getRoles());
        lastSavedAppUser.setMobile(appUser.getMobile());
        lastSavedAppUser.setPhone(appUser.getPhone());
        lastSavedAppUser.setProfileImage(appUser.getProfileImage());
        lastSavedAppUser.setCommentList(appUser.getCommentList());
        lastSavedAppUser.setLikeList(appUser.getLikeList());
        return userRepository.save(lastSavedAppUser);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", allEntries = true),
    })
    public void delete(AppUser user) {
        userRepository.deleteById(user.getId());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "userCache", allEntries = true),
    })
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "userCache", key = "#id")
    public AppUser getById(Long id) {
        Optional<AppUser> optionalAppUser = userRepository.findById(id);
        if(optionalAppUser.isEmpty()){
            throw new NotFoundException("User Not Found!");
        }
        return optionalAppUser.get();
    }

    @Override
    @Cacheable(value = "userCache")
    public AppUser getByName(String name) {
        Optional<AppUser> optionalAppUser = userRepository.findByName(name);
        if(optionalAppUser.isEmpty()) {
            throw new NotFoundException("User Not Fount!");
        }
        return optionalAppUser.get();
    }

    @Override
    @Cacheable(value = "userCache")
    public AppUser getByUsername(String username) {
        Optional<AppUser> optionalAppUser = userRepository.findByUsername(username);
        if(optionalAppUser.isEmpty()) {
            throw new NotFoundException("User Not Fount!");
        }
        return optionalAppUser.get();
    }

    @Override
    @Cacheable(value = "userCache")
    public AppUser getByEmail(String email) {
        Optional<AppUser> optionalAppUser = userRepository.findByEmail(email);
        if(optionalAppUser.isEmpty()) {
            throw new NotFoundException("User Not Fount!");
        }
        return optionalAppUser.get();
    }

    @Override
    @Cacheable(value = "userCache")
    public boolean hasUserWithUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Cacheable(value = "userCache")
    public boolean hasUserWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<AppUser> getAll() {
        return (List<AppUser>) userRepository.findAll();
    }

    @Override
    @Cacheable(value = "userCache")
    public Page<AppUser> paging(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    @Cacheable(value = "userCache")
    public List<AppUser> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<AppUser> userSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> userSpecification.add(criteria));
        return userRepository.findAll(userSpecification);
    }
}

