package otus.spring.albot.lesson22.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@ToString(exclude = {"notes", "orders"})
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;
    @ManyToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private List<Order> orders;
}
