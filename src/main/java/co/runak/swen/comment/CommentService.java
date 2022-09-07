package co.runak.swen.comment;

import java.util.List;


public interface CommentService {
    Comment save(Comment comment);
    Comment update(Comment comment);
    void delete(Long id);
    Comment getById(Long id);
    List<Comment> getByPlaceId(Long placeId);
    List<Comment> getByUserId(Long userId);
    List<Comment> getAll();
}
