package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "createddate")
    private LocalDateTime createddate;

    @Column(name = "modifieddate")
    private LocalDateTime modifieddate;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
    private List<TransactionCustomerEntity> transactionCustomerEntities;

    public TransactionEntity() {
    }

    public TransactionEntity(Long id, String name, String code, String createdby, String modifiedby, LocalDateTime createddate, LocalDateTime modifieddate, List<TransactionCustomerEntity> transactionCustomerEntities) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createdby = createdby;
        this.modifiedby = modifiedby;
        this.createddate = createddate;
        this.modifieddate = modifieddate;
        this.transactionCustomerEntities = transactionCustomerEntities;
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

    public List<TransactionCustomerEntity> getTransactionCustomerEntities() {
        return transactionCustomerEntities;
    }

    public void setTransactionCustomerEntities(List<TransactionCustomerEntity> transactionCustomerEntities) {
        this.transactionCustomerEntities = transactionCustomerEntities;
    }
}
