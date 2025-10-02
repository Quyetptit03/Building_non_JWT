package vn.quyetptit03.springbootshop_non_jwt.dto;

public class BuildingDTO {
    private String name;
    private String address;
    private String numberOfBasement;

    public BuildingDTO() {
    }

    public BuildingDTO(String name, String address, String numberOfBasement) {
        this.name = name;
        this.address = address;
        this.numberOfBasement = numberOfBasement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(String numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }
}
