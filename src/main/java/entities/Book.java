package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue
    @Column(name="id")
    public Long ID;

    @NotNull
    @Column(name="name")
    public String Name;
}
