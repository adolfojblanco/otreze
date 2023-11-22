package es.otreze.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cashBoxDetails")
public class CashBoxDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal tpv;

    private Integer fiveBill;       // 5
    private Integer tenBill;        // 10
    private Integer twentyBill;     // 20
    private Integer fiftyBill;      // 50
    private Integer hundredBill;    // 100
    private Integer twoHundredBill; // 200

    private Integer oneCentCoin;    // 0,01
    private Integer twoCentCoin;    // 0,02
    private Integer fiveCentCoin;   // 0,05
    private Integer tenCentCoin;    // 0,10
    private Integer twentyCentsCoin;// 0,20
    private Integer fiftyCentsCoin; // 0,50
    private Integer oneCoin;        // 1
    private Integer twoCoin;        // 2

    @OneToOne
    private CashBox cashBox;
}
