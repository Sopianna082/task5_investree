package com.investree.demo.controller;

import com.investree.demo.model.User;
import com.investree.demo.utils.ResponseWrapper;
import com.investree.demo.view.UserService;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
@SuppressWarnings("rawtypes")
public class UsersController {

    private final UserService service;
    private final ResponseWrapper wrapper;

    @PostMapping
    public ResponseEntity<Map> save(
            @RequestBody User users
    ) {
        return wrapper.getResult(service.save(users));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<User>> list(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {

        return ResponseEntity.ok(service.list(page, size));
    }

}
