package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "userrole")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "modifiedtime")
    private LocalDateTime modifiedtime;

    @Column(name = "createdtime")
    private LocalDateTime createdtime;

    @ManyToOne
    @JoinColumn(name = "roleid")
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    public UserRoleEntity() {
    }

    public UserRoleEntity(Long id, String modifiedby, String createdby, LocalDateTime modifiedtime, LocalDateTime createdtime, RoleEntity role, UserEntity user) {
        this.id = id;
        this.modifiedby = modifiedby;
        this.createdby = createdby;
        this.modifiedtime = modifiedtime;
        this.createdtime = createdtime;
        this.role = role;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
