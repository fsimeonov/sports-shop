package mk.ukim.finki.emt.sportsshop.models;


import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "status")
    private String status;

    @Column(name = "charge_id")
    private String chargeId;

    @Column(name = "balance_transaction")
    private String balanceTransaction;


    public Transactions() {
    }

    public Transactions(String status, String chargeId, String balanceTransaction) {
        this.status = status;
        this.chargeId = chargeId;
        this.balanceTransaction = balanceTransaction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getBalanceTransaction() {
        return balanceTransaction;
    }

    public void setBalanceTransaction(String balanceTransaction) {
        this.balanceTransaction = balanceTransaction;
    }
}
