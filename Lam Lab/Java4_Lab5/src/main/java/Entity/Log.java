package Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "Logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Url",columnDefinition = "nvarchar(255)")
    private String url;
    @Column(name = "Time",columnDefinition = "nvarchar(100)")
    private String  time;
    @Column(name = "Username",columnDefinition = "nvarchar(10)")
    private String username;

    public Log(String url, String time, String username) {
        super();
        this.url = url;
        this.time = time;
        this.username = username;
    }
}
