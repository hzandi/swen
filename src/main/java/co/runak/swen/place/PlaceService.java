package co.runak.swen.place;

import java.util.List;

public interface PlaceService {
    Place save(Place place);
    Place update(Place place);
    void delete(Long id);
    Place getById(Long id);
    List<Place> getAll();
}
