package com.pilot.entties;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "building_details")
@SQLDelete(sql = "UPDATE building_details SET deleted = true WHERE id = ?")
public class BuildingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    private String geometryType;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_coordinate_hash_codes", referencedColumnName = "id")
    private CoordinateHashCodes coordinateHashCodes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_building", referencedColumnName = "id")
    private Building building;
    private String properties;
    @Column(nullable = false)
    private boolean deleted;

}
