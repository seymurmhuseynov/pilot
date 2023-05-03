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
@ToString

@Entity
@Table(name = "coordinate_hash_codes")
public class CoordinateHashCodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "coordinate_building_coordinates", joinColumns = @JoinColumn(name = "id_coordinate_hash_codes"), inverseJoinColumns = @JoinColumn(name = "id_building_coordinates"))
    private List<BuildingCoordinate> buildingCoordinates;
    private int hashCode;
}
