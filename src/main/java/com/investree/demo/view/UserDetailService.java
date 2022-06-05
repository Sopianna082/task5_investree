package com.investree.demo.view;

import com.investree.demo.model.UserDetail;
import java.util.Map;
import org.springframework.data.domain.Page;

@SuppressWarnings("rawtypes")
public interface UserDetailService {

    Map save(UserDetail userDetail);

    Page<UserDetail> list(Integer page, Integer size);

}
