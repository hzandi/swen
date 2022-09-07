package co.runak.swen.image;

import java.util.List;


public interface ImageService {
    Image save(Image image);
    Image update(Image image);
    void delete(Long id);
    Image getById(Long id);
    List<Image> getByPlaceId(Long placeId);
    List<Image> getByUserId(Long userId);
    List<Image> getAll();
}
