package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Favorites", uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoID"})})
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "UserId")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name = "VideoId")
	private Video video;
	@Temporal(TemporalType.DATE)
	private Date likedate;
}
