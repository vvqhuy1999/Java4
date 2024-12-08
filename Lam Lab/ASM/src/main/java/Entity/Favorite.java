package Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Favorites", uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoID"})})
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;
	@Temporal(TemporalType.DATE)
	private Date likeDate;

	public Favorite(Video video, User user, Date likeDate) {
		this.video = video;
		this.user = user;
		this.likeDate = likeDate;
	}
}
