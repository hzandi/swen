package co.runak.swen.comment;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toComment(CommentDTO commentDTO);

    CommentDTO toCommentDTO(Comment comment);

    List<Comment> toCommentList(List<CommentDTO> commentDTOS);

    List<CommentDTO> toCommentDTOList(List<Comment> comments);
}
