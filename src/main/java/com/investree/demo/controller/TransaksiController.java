package com.investree.demo.controller;

import com.investree.demo.model.Transaksi;
import com.investree.demo.utils.ResponseWrapper;
import java.util.HashMap;
import java.util.Map;

import com.investree.demo.view.TransaksiService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/transaksi")
@SuppressWarnings({"rawtypes"})
public class TransaksiController {

    private final TransaksiService service;
    private final ResponseWrapper wrapper;

    @PostMapping
    public ResponseEntity<Map> save(@RequestBody Transaksi transaction) {
        return wrapper.getResult(service.save(transaction));
    }

    @PutMapping
    public ResponseEntity<Map> updateStatus(@RequestBody Transaksi transaction) {
        return wrapper.getResult(service.updateStatus(transaction));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Transaksi>> list(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "") String status
    ) {
        return ResponseEntity.ok(service.list(status, page, size));
    }

}
