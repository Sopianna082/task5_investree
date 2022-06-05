package com.investree.demo.view;

import com.investree.demo.model.PaymentHistory;
import java.util.Map;
import org.springframework.data.domain.Page;

@SuppressWarnings("rawtypes")
public interface PaymentHistoryService {

    Map save(PaymentHistory paymentHistory);

    Page<PaymentHistory> list(Integer page, Integer size);

}
