package co.runak.swen.like;

import org.mapstruct.Mapper;
import java.util.List;


@Mapper(componentModel = "spring")
public interface LikeMapper {

    Like toLike(LikeDTO likeDTO);

    LikeDTO toLikeDTO(Like like);

    List<Like> toLikeList(List<LikeDTO> likeDTOS);

    List<LikeDTO> toLikeDTOList(List<Like> likes);
}
