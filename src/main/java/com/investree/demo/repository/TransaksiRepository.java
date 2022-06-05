package com.investree.demo.repository;

import com.investree.demo.model.Transaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends PagingAndSortingRepository<Transaksi, Long> {
    @Query("SELECT t FROM transaksi t WHERE t.status LIKE :status")
    Page<Transaksi> findAll(
            @Param(value = "status") String status, Pageable pageable
    );
}
