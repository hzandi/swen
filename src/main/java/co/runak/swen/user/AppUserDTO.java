package co.runak.swen.user;

import co.runak.swen.comment.Comment;
import co.runak.swen.image.Image;
import co.runak.swen.like.Like;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
public class AppUserDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String name;

    @ApiModelProperty(required = true, hidden = false)
    private String username;

    @ApiModelProperty(required = true, hidden = false)
    private String email;

    @ApiModelProperty(required = true, hidden = false)
    private Set<Role> roles;

    @ApiModelProperty(required = true, hidden = false)
    private String password;

    @ApiModelProperty(required = true, hidden = false)
    private String mobile;

    @ApiModelProperty(required = true, hidden = false)
    private String phone;

    @ApiModelProperty(required = true, hidden = false)
    private Image profileImage;

    @ApiModelProperty(required = false, hidden = true)
    private List<Comment> commentList;

    @ApiModelProperty(required = false, hidden = true)
    private List<Like> likeList;
}
