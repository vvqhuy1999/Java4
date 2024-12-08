package entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "Users")
public class user {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String Id;
	@Column(name = "Password")
	private String Password;
	@Column(name = "FullName", columnDefinition = "Nvarchar(50)")
	private String FullName;
	@Column(name ="Email")
	private String Email;
	@Column(name = "Admin")
	private Boolean Admin = false;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Favorite> favorites = new ArrayList<>();
	public user() {
		super();
	}

	public user(String id, String password, String fullName, String email, Boolean admin) {
		super();
		Id = id;
		Password = password;
		FullName = fullName;
		Email = email;
		Admin = admin;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Boolean getAdmin() {
		return Admin;
	}

	public void setAdmin(Boolean admin) {
		Admin = admin;
	}
    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }
	
}
