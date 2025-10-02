package vn.quyetptit03.springbootshop_non_jwt.dto;

public class BuildingDTO {
    private String name;
    private String street;
    private String ward;
    private String numberOfBasement;

    public BuildingDTO() {
    }

    public BuildingDTO(String name, String street, String ward, String numberOfBasement) {
        this.name = name;
        this.street = street;
        this.ward = ward;
        this.numberOfBasement = numberOfBasement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(String numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    @Override
    public String toString() {
        return "BuildingDTO{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", ward='" + ward + '\'' +
                ", numberOfBasement='" + numberOfBasement + '\'' +
                '}';
    }
}
