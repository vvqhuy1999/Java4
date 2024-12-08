package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "Id")
	private String id;
	@Column(name = "Password")
	private String password;
	@Column(name = "Fullname")
	private String fullname;
	@Column(name = "Email")
	private String email;
	@Column(name = "Admin")
	private Boolean admin;
	@OneToMany(mappedBy = "user")
	private List<Favorite> favories;
    @OneToMany(mappedBy = "user")
    private List<Share> shares;
}
