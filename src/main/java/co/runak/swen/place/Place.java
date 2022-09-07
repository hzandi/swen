package co.runak.swen.place;

import co.runak.swen.comment.Comment;
import co.runak.swen.common.BaseEntity;
import co.runak.swen.image.Image;
import co.runak.swen.like.Like;
import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "tbl_place")
@Data
@Audited
public class Place extends BaseEntity {

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "start_service")
    private Integer startService;

    @NotNull
    @Column(name = "end_service")
    private Integer endService;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "place_location")
    private Point<G2D> location;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place", cascade = CascadeType.ALL)
    private List<Like> likeList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place", cascade = CascadeType.ALL)
    private List<Image> images;

}
