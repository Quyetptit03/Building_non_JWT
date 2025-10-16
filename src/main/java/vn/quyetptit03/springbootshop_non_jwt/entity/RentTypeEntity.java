package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "renttype")
public class RentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = " name")
    private String name;

    @OneToMany(mappedBy = "rentType", fetch = FetchType.LAZY)
    private List<BuildingRenttypeEntity> buildingRenttypeEntities = new ArrayList<>();

    public RentTypeEntity() {
    }

    public RentTypeEntity(Long id, String code, String name, List<BuildingRenttypeEntity> buildingRenttypeEntities) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.buildingRenttypeEntities = buildingRenttypeEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingRenttypeEntity> getBuildingRenttypeEntities() {
        return buildingRenttypeEntities;
    }

    public void setBuildingRenttypeEntities(List<BuildingRenttypeEntity> buildingRenttypeEntities) {
        this.buildingRenttypeEntities = buildingRenttypeEntities;
    }
}
