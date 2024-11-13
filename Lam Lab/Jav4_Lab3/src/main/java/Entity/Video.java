package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "Videos", uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoID"})})
public class Video {
    @Id
    @Column(name = "Id", columnDefinition = "nvarchar(10)")
    private String id;
    @Column(name = "Title", columnDefinition = "nvarchar(100)")
    private String title;
    @Column(name = "Poster", columnDefinition = "nvarchar(255)")
    private String poster;
    @Column(name = "Views")
    private Integer views;
    @Column(name = "Description", columnDefinition = "nvarchar(255)")
    private String description;
    @Column(name = "Active")
    private int active;

    @OneToMany(mappedBy = "video" , fetch = FetchType.LAZY)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "video" , fetch = FetchType.LAZY)
    private List<Share> shares;

}
