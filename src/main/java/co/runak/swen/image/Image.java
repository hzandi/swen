package co.runak.swen.image;

import co.runak.swen.common.BaseEntity;
import co.runak.swen.place.Place;
import co.runak.swen.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tbl_images")
@Data
public class Image extends BaseEntity {

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

}


