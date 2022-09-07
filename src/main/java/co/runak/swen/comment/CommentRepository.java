package co.runak.swen.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends
        PagingAndSortingRepository<Comment, Long>,
        JpaSpecificationExecutor<Comment> {

    Comment findByText(String text);
    List<Comment> findAllByUser_Id(Long userId);
    List<Comment> findAllByPlace_Id(Long placeId);
    Page<Comment> findAll(Pageable pageable);
    List<Comment> findAll(Specification<Comment> specification);
}
