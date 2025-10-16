package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "createddate")
    private LocalDateTime createddate;

    @Column(name = "modifieddate")
    private LocalDateTime modifieddate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AssignmentBuildingEntity> assignmentBuilding;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRoleEntity> userRoleEntities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AssignmentCustomerEntity> assignmentCustomerEntities;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, String fullname, String phone, String createdby, String modifiedby, LocalDateTime createddate, LocalDateTime modifieddate, List<AssignmentBuildingEntity> assignmentBuilding, List<UserRoleEntity> userRoleEntities, List<AssignmentCustomerEntity> assignmentCustomerEntities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.createdby = createdby;
        this.modifiedby = modifiedby;
        this.createddate = createddate;
        this.modifieddate = modifieddate;
        this.assignmentBuilding = assignmentBuilding;
        this.userRoleEntities = userRoleEntities;
        this.assignmentCustomerEntities = assignmentCustomerEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public LocalDateTime getCreateddate() {
        return createddate;
    }

    public void setCreateddate(LocalDateTime createddate) {
        this.createddate = createddate;
    }

    public LocalDateTime getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(LocalDateTime modifieddate) {
        this.modifieddate = modifieddate;
    }

    public List<AssignmentBuildingEntity> getAssignmentBuilding() {
        return assignmentBuilding;
    }

    public void setAssignmentBuilding(List<AssignmentBuildingEntity> assignmentBuilding) {
        this.assignmentBuilding = assignmentBuilding;
    }

    public List<UserRoleEntity> getUserRoleEntities() {
        return userRoleEntities;
    }

    public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
        this.userRoleEntities = userRoleEntities;
    }

    public List<AssignmentCustomerEntity> getAssignmentCustomerEntities() {
        return assignmentCustomerEntities;
    }

    public void setAssignmentCustomerEntities(List<AssignmentCustomerEntity> assignmentCustomerEntities) {
        this.assignmentCustomerEntities = assignmentCustomerEntities;
    }
}
