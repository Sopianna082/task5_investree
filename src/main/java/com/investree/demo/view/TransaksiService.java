package com.investree.demo.view;

import com.investree.demo.model.Transaksi;
import java.util.Map;
import org.springframework.data.domain.Page;

@SuppressWarnings("rawtypes")
public interface TransaksiService {

    Map save(Transaksi transaction);

    Map updateStatus(Transaksi transaction);

    Page<Transaksi> list(String status, Integer page, Integer size);
}