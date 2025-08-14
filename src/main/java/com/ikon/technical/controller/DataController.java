package com.ikon.technical.controller;

import com.ikon.technical.common.CommonResponse;
import com.ikon.technical.exception.NegativeParamException;
import com.ikon.technical.service.DataService;
import com.ikon.technical.service.pojo.DataPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    DataService service;

    @GetMapping
    public CommonResponse<Page<DataPojo>> getPage(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        CommonResponse<Page<DataPojo>> result = new CommonResponse<>();
        try {
            Page<DataPojo> pojo = service.getPageable(page, size);
            if(pojo != null) {
                result.setStatus(200);
                result.setMessage("Success retrieve data");
                result.setDatas(pojo);
            }
        } catch (NegativeParamException e) {
            result.setMessage(e.getMessage());
            result.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return result;
    }
}
