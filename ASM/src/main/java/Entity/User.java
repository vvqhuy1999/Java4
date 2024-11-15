package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "Id", columnDefinition = "nvarchar(10)")
	private String id;
	@Column(name = "Password", columnDefinition = "nvarchar(50)")
	private String password;
	@Column(name = "Email", columnDefinition = "nvarchar(100)")
	private String email;
	@Column(name = "Fullname", columnDefinition = "nvarchar(100)")
	private String fullname;
	@Column(name = "Admin")
	private Boolean admin;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	private List<Share> shares;

}
