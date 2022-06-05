package com.investree.demo.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class ResponseWrapper {

    public ResponseEntity<Map> getResult(Map data) {
        if (data.get("message") != null) {
            return ResponseEntity.internalServerError()
                    .body(mapResponse(500, "gagal", data));
        }

        return ResponseEntity.ok(
                mapResponse(
                        200,
                        "sukses",
                        data)
        );
    }

    public Map mapResponse(int code, String status, Map data) {
        Map mapResponse = new HashMap();
        mapResponse.put("data", data);
        mapResponse.put("status", status);
        mapResponse.put("code", code);
        return mapResponse;
    }

}