package otus.spring.albot.lesson17.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String comment;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "rate")
    private int rate;
}
