package co.runak.swen.like;

import co.runak.swen.common.BaseDTO;
import co.runak.swen.place.Place;
import co.runak.swen.user.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LikeDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private Place place;

    @ApiModelProperty(required = true, hidden = false)
    private AppUser user;
}
