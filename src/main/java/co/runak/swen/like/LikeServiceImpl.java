package co.runak.swen.like;

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
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "likeCache", allEntries = true),
    })
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "likeCache", allEntries = true),
    })
    public Like update(Like like) {
        Like lastSavedLike = getById(like.getId());
        lastSavedLike.setPlace(like.getPlace());
        lastSavedLike.setUser(like.getUser());
        return likeRepository.save(lastSavedLike);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "likeCache", allEntries = true),
    })
    public void delete(Long id) {
        likeRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "likeCache", key = "#id")
    public Like getById(Long id) {
        Optional<Like> optionalVehicle = likeRepository.findById(id);
        if (optionalVehicle.isEmpty()) {
            throw new NotFoundException("Like Not Found!");
        }
        return optionalVehicle.get();
    }

    @Override
    @Cacheable(value = "likeCache")
    public List<Like> getByPlaceId(Long placeId) {
        return likeRepository.findAllByPlace_Id(placeId);
    }

    @Override
    @Cacheable(value = "likeCache")
    public List<Like> getByUserId(Long userId) {
        return likeRepository.findAllByPlace_Id(userId);
    }

    @Override
    @Cacheable(value = "likeCache")
    public List<Like> getAll() {
        return (List<Like>) likeRepository.findAll();
    }
}


