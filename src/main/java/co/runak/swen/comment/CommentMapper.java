package co.runak.swen.comment;

import java.util.List;

public interface CommentMapper {

    Comment toComment(CommentDTO commentDTO);

    CommentDTO toCommentDTO(Comment comment);

    List<Comment> toCommentList(List<CommentDTO> commentDTOS);

    List<CommentDTO> toCommentDTOList(List<Comment> comments);
}
