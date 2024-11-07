package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "employees")
public class employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", columnDefinition = "nvarchar(50)")
    private String name;
    @Column(name = "salary", precision = 2)
    private Double salary;

    public employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }
}
