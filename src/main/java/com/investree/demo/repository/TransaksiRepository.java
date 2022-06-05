package com.investree.demo.repository;

import com.investree.demo.model.Transaksi;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends PagingAndSortingRepository<Transaksi, Long> {

}
