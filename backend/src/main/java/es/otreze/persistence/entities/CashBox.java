package es.otreze.persistence.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cashBoxes")
public class CashBox {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal openBalance;
    private BigDecimal totalCashBoxCount;
    private BigDecimal totalBillCount;
    private BigDecimal totalCoinCount;
    private BigDecimal totalTpv;
    private BigDecimal totalMoneyIn;
    private BigDecimal totalMoneyOut;
    private BigDecimal totalBoxOfTheDay;

    @Temporal(TemporalType.DATE)
    private Date openDate;

    @Temporal(TemporalType.DATE)
    private Date closeDate;

    private String comment;

    private int numberOfSales;

    private boolean isOpen;

    @PrePersist
    public void prePersist() {
        openDate = new Date();
    }



}
