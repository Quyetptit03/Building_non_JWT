package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "modifiedtime")
    private LocalDateTime modifiedtime;

    @Column(name = "createdtime")
    private LocalDateTime createdtime;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<UserRoleEntity> userRoles;

    public RoleEntity() {
    }

    public RoleEntity(Long id, String name, String code, String modifiedby, String createdby, LocalDateTime modifiedtime, LocalDateTime createdtime, List<UserRoleEntity> userRoles) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.modifiedby = modifiedby;
        this.createdby = createdby;
        this.modifiedtime = modifiedtime;
        this.createdtime = createdtime;
        this.userRoles = userRoles;
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

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public LocalDateTime getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(LocalDateTime modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public LocalDateTime getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
    }

    public List<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
