package com.investree.demo.view;

import com.investree.demo.model.Transaksi;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface TransaksiService {

    Map save(Transaksi transaction);

    Map updateStatus(Transaksi transaction);

}