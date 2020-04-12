package otus.spring.albot.lesson22.entity;

import lombok.Data;
import otus.spring.albot.lesson22.model.AuthorityType;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private AuthorityType name;
}
