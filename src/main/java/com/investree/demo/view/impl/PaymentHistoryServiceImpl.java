package com.investree.demo.view.impl;

import com.investree.demo.model.PaymentHistory;
import com.investree.demo.repository.PaymentHistoryRepository;
import com.investree.demo.view.PaymentHistoryService;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository repository;

    @Override
    public Map save(PaymentHistory paymentHistory) {
        try {
            return mappingData(repository.save(paymentHistory));
        } catch (Exception e) {
            e.printStackTrace();
            return mapError(e);
        }
    }

    @Override
    public Page<PaymentHistory> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    private Map mappingData(PaymentHistory paymentHistory) {
        Map mapData = new HashMap();
        mapData.put("id", paymentHistory.getId());

        return mapData;
    }

    private Map mapError(Exception e) {
        Map mapError = new HashMap();
        mapError.put("message", e.getMessage());
        return mapError;
    }

}
