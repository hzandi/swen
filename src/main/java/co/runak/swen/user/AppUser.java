package co.runak.swen.user;

import co.runak.swen.comment.Comment;
import co.runak.swen.common.BaseEntity;
import co.runak.swen.image.Image;
import co.runak.swen.like.Like;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tbl_users")
@Data
public class AppUser extends BaseEntity {

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @ElementCollection(targetClass = Role.class)
    @JoinTable(name = "tbl_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profile_image")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Image profileImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likeList;
}
