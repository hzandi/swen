package co.runak.swen.place;


import co.runak.swen.comment.Comment;
import co.runak.swen.common.BaseDTO;
import co.runak.swen.like.Like;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlaceDTO extends BaseDTO {


    @ApiModelProperty(required = true, hidden = false)
    private String title;

    @ApiModelProperty(required = true, hidden = false)
    private Integer startService;

    @ApiModelProperty(required = true, hidden = false)
    private Integer endService;

    @ApiModelProperty(required = true, hidden = false)

    private String address;

    @ApiModelProperty(required = true, hidden = false)
    private Type type;

    @ApiModelProperty(required = true, hidden = false)
    private LocationDTO locationDTO;

    @ApiModelProperty(required = false, hidden = true)
    private List<Comment> commentList;

    @ApiModelProperty(required = false, hidden = true)
    private List<Like> likeList;

}
