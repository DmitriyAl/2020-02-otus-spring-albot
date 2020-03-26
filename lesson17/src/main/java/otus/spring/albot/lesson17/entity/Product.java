package otus.spring.albot.lesson17.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name ="description")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;
    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Note> notes;
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
