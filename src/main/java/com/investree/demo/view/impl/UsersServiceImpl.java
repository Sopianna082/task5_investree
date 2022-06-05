package com.investree.demo.view.impl;

import com.investree.demo.model.User;
import com.investree.demo.repository.UserRepository;
import com.investree.demo.view.UserService;
import com.investree.demo.view.UserService;
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
public class UsersServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public Map save(User users) {
        try {
            return mappingData(repository.save(users));
        } catch (Exception e) {
            e.printStackTrace();
            return mapError(e);
        }
    }

    @Override
    public Page<User> list(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return repository.findAll(pageable);
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }

    private Map mappingData(User users) {
        Map mapData = new HashMap();
        mapData.put("id", users.getId());
        mapData.put("username", users.getUsername());
        mapData.put("password", users.getPassword());
        mapData.put("isActive", users.getIsActive());

        return mapData;
    }

    private Map mapError(Exception e) {
        Map mapError = new HashMap();
        mapError.put("message", e.getMessage());
        return mapError;
    }

}