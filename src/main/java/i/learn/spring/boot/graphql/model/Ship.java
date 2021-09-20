package i.learn.spring.boot.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Ship {
    @Id
    private Integer serialNo;
    private String name;
    private Float weight;
    private String captain;
    private String[] crew;
    private Integer fabricationYear;

}
