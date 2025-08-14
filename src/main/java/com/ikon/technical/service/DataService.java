package com.ikon.technical.service;

import com.ikon.technical.exception.NegativeParamException;
import com.ikon.technical.service.pojo.DataPojo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DataService {

    Page<DataPojo> getPageable(int page, int size) throws NegativeParamException;
}
