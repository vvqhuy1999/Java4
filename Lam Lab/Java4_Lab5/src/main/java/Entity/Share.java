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
@Table(name = "Shares")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn (name = "UserId")
    private User user;
    @ManyToOne
    @JoinColumn (name = "VideoId")
    private Video video;
    @Column(name = "Emails", length = 100, nullable = false, unique = true)
    private String email;
    @Temporal(TemporalType.DATE)
    private Date sharedate;

}
