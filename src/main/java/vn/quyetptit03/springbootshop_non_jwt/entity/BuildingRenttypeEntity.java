package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "buildingRenttype")
public class BuildingRenttypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    private BuildingEntity building;

    @ManyToOne
    @JoinColumn(name = "renttypeId")
    private RentTypeEntity rentType;

    public BuildingRenttypeEntity() {
    }

    public BuildingRenttypeEntity(Long id, BuildingEntity building, RentTypeEntity rentType) {
        this.id = id;
        this.building = building;
        this.rentType = rentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public RentTypeEntity getRentType() {
        return rentType;
    }

    public void setRentType(RentTypeEntity rentType) {
        this.rentType = rentType;
    }
}
