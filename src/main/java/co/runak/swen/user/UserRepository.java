package co.runak.swen.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends
        PagingAndSortingRepository<AppUser, Long>,
        JpaSpecificationExecutor<AppUser> {

    Optional<AppUser> findByName(String name);
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Page<AppUser> findAll(Pageable pageable);
    List<AppUser> findAll(Specification<AppUser> specification);
}
