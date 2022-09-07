package co.runak.swen.comment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/comment/")
@AllArgsConstructor
public class CommentController {

    private final CommentService service;
    private CommentMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity save(@RequestBody CommentDTO commentDTO) {
        Comment comment = mapper.toComment(commentDTO);
        service.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<CommentDTO> getById(@PathVariable Long id) {
        Comment comment = service.getById(id);
        CommentDTO commentDTO = mapper.toCommentDTO(comment);
        return ResponseEntity.ok(commentDTO);
    }

    @GetMapping("/v1")
    public ResponseEntity<List<CommentDTO>> getAll() {

        List<Comment> commentList = service.getAll();
        List<CommentDTO> commentDTOS = mapper.toCommentDTOList(commentList);

        return ResponseEntity.ok(commentDTOS);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}

