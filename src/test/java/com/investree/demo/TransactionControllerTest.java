package com.investree.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.investree.demo.controller.TransaksiController;
import com.investree.demo.model.Transaksi;
import com.investree.demo.model.User;
import com.investree.demo.utils.ResponseWrapper;
import com.investree.demo.view.TransaksiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TransaksiController.class)
@SuppressWarnings({"rawtypes", "unchecked"})
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransaksiService service;

    @MockBean
    private ResponseWrapper wrapper;

    @Test
    public void restTemplateSave() throws Exception {
        // arrange
        User users1 = new User();
        users1.setId(1L);
        users1.setUsername("username");
        users1.setPassword("password");
        users1.setIsActive(true);

        User users2 = new User();
        users2.setId(2L);
        users2.setUsername("username1");
        users2.setPassword("password1");
        users2.setIsActive(true);

        Transaksi tx = new Transaksi();
        tx.setId(1L);
        tx.setTenor(1);
        tx.setTotalLoan(new BigDecimal(1000000));
        tx.setPercentInterest(1.0);
        tx.setStatus("belum lunas");
        tx.setBorrowerUser(users1);
        tx.setUserBorrow(users2);

        Map result = new HashMap();
        result.put("id", 1L);
        result.put("tenor", 1);
        result.put("totalPinjaman", 1000000);
        result.put("bungaPersen", 1.0);
        result.put("status", "belum lunas");
        result.put("peminjam", users1);
        result.put("meminjam", users2);

        String jsonResult = new ObjectMapper().writeValueAsString(tx);

        when(service.save(tx)).thenReturn(result);

        when(wrapper.getResult(result)).thenReturn(ResponseEntity.ok(result));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/transaksi")
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonResult)
                .contentType(MediaType.APPLICATION_JSON);

        // act assert
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
