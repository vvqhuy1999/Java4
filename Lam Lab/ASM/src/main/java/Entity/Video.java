package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import java.util.List;
 
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Videos")
public class Video {
	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Title")
	private String title;
	@Column(name = "Link")
	private String link;
	@Column(name = "Poster")
	private String poster;
	@Column(name = "Description")
	private String description;
	@Column(name = "Active")
	private boolean active;
	@Column(name = "Views")
	private int views;
	
	@OneToMany(mappedBy = "video")
	private List <Favorite> favorites;
    @OneToMany(mappedBy = "video")
    private List<Share> shares;
}
