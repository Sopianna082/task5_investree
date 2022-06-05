package com.investree.demo.view.impl;

import com.investree.demo.model.UserDetail;
import com.investree.demo.repository.UserDetailRepository;
import com.investree.demo.view.UserDetailService;
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
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailRepository repository;

    @Override
    public Map save(UserDetail userDetail) {
        try {
            return mappingData(repository.save(userDetail));
        } catch (Exception e) {
            e.printStackTrace();
            return mapError(e);
        }
    }

    @Override
    public Page<UserDetail> list(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    private Map mappingData(UserDetail userDetail) {
        Map mapData = new HashMap();
        mapData.put("id", userDetail.getId());

        return mapData;
    }

    private Map mapError(Exception e) {
        Map mapError = new HashMap();
        mapError.put("message", e.getMessage());
        return mapError;
    }

}