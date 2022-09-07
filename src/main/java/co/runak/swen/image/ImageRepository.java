package co.runak.swen.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ImageRepository extends
        PagingAndSortingRepository<Image, Long>,
        JpaSpecificationExecutor<Image> {

    Image findByName(String name);
    List<Image> findAllByUser_Id(Long userId);
    List<Image> findAllByPlace_Id(Long placeId);
    Page<Image> findAll(Pageable pageable);
    List<Image> findAll(Specification<Image> specification);
}
