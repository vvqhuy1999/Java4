package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Id", columnDefinition = "nvarchar(20)")
    private String id;
    @Column(name = "Password", columnDefinition = "nvarchar(50)")
    private String password;
    @Column(name = "Fullname", columnDefinition = "nvarchar(50)")
    private String fullname;
    @Column(name = "Email", columnDefinition = "nvarchar(50)")
    private String email;
    @Column(name = "Admin")
    private Boolean admin;

}
