package otus.spring.albot.project.entity;

import lombok.Data;
import otus.spring.albot.project.security.model.AuthorityType;

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