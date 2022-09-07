package co.runak.swen.like;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface LikeRepository extends
        PagingAndSortingRepository<Like, Long>,
        JpaSpecificationExecutor<Like> {

    List<Like> findAllByUser_Id(Long userId);
    List<Like> findAllByPlace_Id(Long placeId);
    Page<Like> findAll(Pageable pageable);
    List<Like> findAll(Specification<Like> specification);
}
