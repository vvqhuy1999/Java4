package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private user user;

    @ManyToOne
    @JoinColumn(name = "VideoId", nullable = false)
    private video video;

    @Column(name = "LikeDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;

    public Favorite() {
        super();
    }

    public Favorite(user user, video video, Date likeDate) {
        this.user = user;
        this.video = video;
        this.likeDate = likeDate;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public video getVideo() {
        return video;
    }

    public void setVideo(video video) {
        this.video = video;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }
}