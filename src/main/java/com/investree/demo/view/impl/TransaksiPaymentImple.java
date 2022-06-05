package com.investree.demo.view.impl;

import com.investree.demo.model.Transaksi;
import com.investree.demo.repository.TransaksiRepository;
import com.investree.demo.view.TransaksiService;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
public class TransaksiPaymentImple implements TransaksiService {

    private final TransaksiRepository repository;

    @Override
    public Map save(Transaksi transaction) {
        try {
            return mappingData(repository.save(transaction));
        } catch (Exception e) {
            e.printStackTrace();
            return mapError(e);
        }
    }

    @Override
    @SneakyThrows
    public Map updateStatus(Transaksi transaction) {
        try {
            Transaksi trxFromDb = repository.findById(transaction.getId())
                    .orElseThrow(() -> new Exception("Transaksi tidak ditemukan"));

            trxFromDb.setId(transaction.getId());
            trxFromDb.setStatus("lunas");

            return mappingData(repository.save(trxFromDb));
        } catch (Exception e) {
            e.printStackTrace();
            return mapError(e);
        }
    }

    private Map mappingData(Transaksi transaction) {
        Map mapData = new HashMap();
        mapData.put("id", transaction.getId());
        mapData.put("tenor", transaction.getTenor());
        mapData.put("totalPinjaman", transaction.getTotalLoan());
        mapData.put("bungaPersen", transaction.getPercentInterest());
        mapData.put("status", transaction.getStatus());
        mapData.put("peminjam", transaction.getBorrowerUser());
        mapData.put("meminjam", transaction.getUserBorrow());

        return mapData;
    }

    @Override
    public Page<Transaksi> list(String status, Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);

            if (status.isEmpty()) {
                return repository.findAll(pageable);
            }

            return repository.findAll(status, pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    private Map mapError(Exception e) {
        Map mapError = new HashMap();
        mapError.put("message", e.getMessage());
        return mapError;
    }

}
