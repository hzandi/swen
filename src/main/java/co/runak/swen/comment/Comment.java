package co.runak.swen.comment;

import co.runak.swen.common.BaseEntity;
import co.runak.swen.place.Place;
import co.runak.swen.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_comments")
@Data
public class Comment extends BaseEntity {

    @Column(name = "text")
    private String text;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

}
