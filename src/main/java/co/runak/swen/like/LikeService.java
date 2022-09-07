package co.runak.swen.like;

import java.util.List;


public interface LikeService {
    Like save(Like like);
    Like update(Like like);
    void delete(Long id);
    Like getById(Long id);
    List<Like> getByPlaceId(Long placeId);
    List<Like> getByUserId(Long userId);
    List<Like> getAll();
}
