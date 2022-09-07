package co.runak.swen.image;

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
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "imageCache", allEntries = true),
    })
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "imageCache", allEntries = true),
    })
    public Image update(Image image) {
        Image lastSavedImage = getById(image.getId());
        lastSavedImage.setName(image.getName());
        lastSavedImage.setPlace(image.getPlace());
        lastSavedImage.setUser(image.getUser());
        return imageRepository.save(lastSavedImage);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "imageCache", allEntries = true),
    })
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "imageCache", key = "#id")
    public Image getById(Long id) {
        Optional<Image> optionalVehicle = imageRepository.findById(id);
        if (optionalVehicle.isEmpty()) {
            throw new NotFoundException("Image Not Found!");
        }
        return optionalVehicle.get();
    }

    @Override
    @Cacheable(value = "imageCache")
    public List<Image> getByPlaceId(Long placeId) {
        return imageRepository.findAllByPlace_Id(placeId);
    }

    @Override
    @Cacheable(value = "imageCache")
    public List<Image> getByUserId(Long userId) {
        return imageRepository.findAllByPlace_Id(userId);
    }

    @Override
    @Cacheable(value = "imageCache")
    public List<Image> getAll() {
        return (List<Image>) imageRepository.findAll();
    }
}

