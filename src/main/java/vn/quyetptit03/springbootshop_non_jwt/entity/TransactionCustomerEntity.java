package vn.quyetptit03.springbootshop_non_jwt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactioncustomer")
public class TransactionCustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note")
    private String note;

    @Column(name = "createdby")
    private String createdby;

    @Column(name = "modifiedby")
    private String modifiedby;

    @Column(name = "createddate")
    private LocalDateTime createddate;

    @Column(name = "modifieddate")
    private LocalDateTime modifieddate;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "transactionid")
    private TransactionEntity transaction;

    public TransactionCustomerEntity() {
    }

    public TransactionCustomerEntity(Long id, String note, String createdby, String modifiedby, LocalDateTime createddate, LocalDateTime modifieddate, CustomerEntity customer, TransactionEntity transaction) {
        this.id = id;
        this.note = note;
        this.createdby = createdby;
        this.modifiedby = modifiedby;
        this.createddate = createddate;
        this.modifieddate = modifieddate;
        this.customer = customer;
        this.transaction = transaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }
}
