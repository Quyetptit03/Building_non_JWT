package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "district")
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private List<BuildingEntity> building = new ArrayList<>();

    public DistrictEntity() {
    }

    public DistrictEntity(Long id, String name, String code, List<BuildingEntity> building) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.building = building;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<BuildingEntity> getBuildingEntities() {
        return building;
    }

    public void setBuildingEntities(List<BuildingEntity> building) {
        this.building = building;
    }
}
