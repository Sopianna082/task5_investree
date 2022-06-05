package com.investree.demo.view;

import com.investree.demo.model.User;
import java.util.Map;
import org.springframework.data.domain.Page;

@SuppressWarnings("rawtypes")
public interface UserService {

    Map save(User users);

    Page<User> list(Integer page, Integer size);

}