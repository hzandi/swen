package co.runak.swen.like;

import co.runak.swen.comment.Comment;
import co.runak.swen.common.BaseEntity;
import co.runak.swen.place.Place;
import co.runak.swen.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tbl_likes")
@Data
public class Like extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

}


