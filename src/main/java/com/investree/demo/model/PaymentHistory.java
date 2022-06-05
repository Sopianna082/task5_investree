package com.investree.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pembayaran_ke", nullable = false)
    private Integer paymentCount;

    @Column(name = "jumlah", nullable = false)
    private BigDecimal amount;

    @Column(name = "bukti_pembayaran", nullable = false)
    private String paymentReceipt;

    @OneToMany
    @JoinColumn(name = "id_transaksi", referencedColumnName = "id")
    private Collection<Transaksi> transaction = new ArrayList<>();

}