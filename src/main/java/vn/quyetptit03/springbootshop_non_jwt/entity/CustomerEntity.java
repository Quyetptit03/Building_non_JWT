package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "createddate")
    private LocalDateTime createddate;

    @Column(name = "modifieddate")
    private LocalDateTime modifieddate;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<AssignmentCustomerEntity> assignmentCustomerEntities;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<TransactionCustomerEntity> transactionCustomerEntities;

    public CustomerEntity() {
    }

    public CustomerEntity(Long id, String fullname, String phone, String email, String createdby, String modifiedby, LocalDateTime createddate, LocalDateTime modifieddate, List<AssignmentCustomerEntity> assignmentCustomerEntities, List<TransactionCustomerEntity> transactionCustomerEntities) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.createdby = createdby;
        this.modifiedby = modifiedby;
        this.createddate = createddate;
        this.modifieddate = modifieddate;
        this.assignmentCustomerEntities = assignmentCustomerEntities;
        this.transactionCustomerEntities = transactionCustomerEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<AssignmentCustomerEntity> getAssignmentCustomerEntities() {
        return assignmentCustomerEntities;
    }

    public void setAssignmentCustomerEntities(List<AssignmentCustomerEntity> assignmentCustomerEntities) {
        this.assignmentCustomerEntities = assignmentCustomerEntities;
    }

    public List<TransactionCustomerEntity> getTransactionCustomerEntities() {
        return transactionCustomerEntities;
    }

    public void setTransactionCustomerEntities(List<TransactionCustomerEntity> transactionCustomerEntities) {
        this.transactionCustomerEntities = transactionCustomerEntities;
    }
}
