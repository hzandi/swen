package co.runak.swen.like;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/like/")
@AllArgsConstructor
public class LikeController {

    private final LikeService service;
    private LikeMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody LikeDTO likeDTO) {
        Like like = mapper.toLike(likeDTO);
        service.save(like);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<LikeDTO> getById(@PathVariable Long id) {
        Like like = service.getById(id);
        LikeDTO likeDTO = mapper.toLikeDTO(like);
        return ResponseEntity.ok(likeDTO);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<LikeDTO>> getAll() {

        List<Like> likeList = service.getAll();
        List<LikeDTO> likeDTOS = mapper.toLikeDTOList(likeList);

        return ResponseEntity.ok(likeDTOS);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}


