package co.runak.swen.comment;

import co.runak.swen.common.BaseDTO;
import co.runak.swen.like.Like;
import co.runak.swen.place.Place;
import co.runak.swen.user.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class CommentDTO extends BaseDTO {


    @ApiModelProperty(required = true, hidden = false)
    private String text;

    @ApiModelProperty(required = true, hidden = false)
    private Place place;

    @ApiModelProperty(required = true, hidden = false)
    private AppUser user;

    @ApiModelProperty(required = false, hidden = true)
    private List<Like> likeList;

}
