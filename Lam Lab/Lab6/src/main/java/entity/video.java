package entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Video")
public class video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Idvideo")
    private Integer idv;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Poster", nullable = false)
    private String poster;
    
    @Column(name = "VideoLink", nullable = false)
    private String link;
    
    @Column(name = "Views", nullable = false)
    private Integer views;

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String description;

    @Column(name = "Active", nullable = false)
    private Boolean active;
    
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites = new ArrayList<>();

	public video() {
		super();
	}

	public video(Integer idv, String title, String poster, String link, Integer views, String description,
			Boolean active) {
		super();
		this.idv = idv;
		this.title = title;
		this.poster = poster;
		this.link = link;
		this.views = views;
		this.description = description;
		this.active = active;
	}

	public Integer getIdv() {
		return idv;
	}

	public void setIdv(Integer idv) {
		this.idv = idv;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<Favorite> getFavorites() {
        return favorites;
    }
	  public void setFavorites(List<Favorite> favorites) {
	        this.favorites = favorites;
	    }
	   public void incrementViews() {
	        this.views++;
	    }
	

}