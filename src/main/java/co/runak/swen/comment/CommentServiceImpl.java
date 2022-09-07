package co.runak.swen.comment;

import co.runak.swen.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "commentCache", allEntries = true),
    })
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "commentCache", allEntries = true),
    })
    public Comment update(Comment comment) {
        Comment lastSavedComment = getById(comment.getId());
        lastSavedComment.setText(comment.getText());
        lastSavedComment.setPlace(comment.getPlace());
        lastSavedComment.setUser(comment.getUser());
        return commentRepository.save(lastSavedComment);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "commentCache", allEntries = true),
    })
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "commentCache", key = "#id")
    public Comment getById(Long id) {
        Optional<Comment> optionalVehicle = commentRepository.findById(id);
        if (optionalVehicle.isEmpty()) {
            throw new NotFoundException("Comment Not Found!");
        }
        return optionalVehicle.get();
    }

    @Override
    @Cacheable(value = "commentCache")
    public List<Comment> getByPlaceId(Long placeId) {
        return commentRepository.findAllByPlace_Id(placeId);
    }

    @Override
    @Cacheable(value = "commentCache")
    public List<Comment> getByUserId(Long userId) {
        return commentRepository.findAllByPlace_Id(userId);
    }

    @Override
    @Cacheable(value = "commentCache")
    public List<Comment> getAll() {
        return (List<Comment>) commentRepository.findAll();
    }
}

