package com.pilot.entties;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ways")
public class Way {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String geometryType;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "way_coordinates", joinColumns = @JoinColumn(name = "id_way"), inverseJoinColumns = @JoinColumn(name = "id_coordinate"))
    private List<Coordinate> coordinates;
    private String properties;

}
