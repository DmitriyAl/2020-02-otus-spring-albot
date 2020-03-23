package otus.spring.albot.lesson17.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Note> notes;
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}