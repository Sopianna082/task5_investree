package com.investree.demo.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="transaksi")
public class Transaksi {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="tenor", nullable = false)
    private Integer tenor;

    @Column(name="total_pinjaman", nullable = false)
    private BigDecimal totalLoan;

    @Column(name="bunga_persen", nullable = false)
    private Double percentInterest;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name="id_peminjam", referencedColumnName = "id")
    private User borrowerUser;

    @ManyToOne
    @JoinColumn(name="id_meminjam", referencedColumnName = "id")
    private User userBorrow;

}