package com.ikon.technical.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikon.technical.exception.NegativeParamException;
import com.ikon.technical.model.Data;
import com.ikon.technical.service.DataService;
import com.ikon.technical.service.pojo.DataPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private WebClient client;

    List<DataPojo> toPojoList(List<Data> modelList) {
        List<DataPojo> pojoList = new ArrayList<>();
        if (modelList!= null && !modelList.isEmpty()) {
            for (Data model: modelList) {
                DataPojo pojo = new DataPojo();
                BeanUtils.copyProperties(model, pojo);
                pojoList.add(pojo);
            }
        }
        return pojoList;
    }

    @Override
    public Page<DataPojo> getPageable(int page, int size) throws NegativeParamException {
        try {
            validationInput(page, size);
            String jsonResponse = client.get()
                    .uri("/posts")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            ObjectMapper mapper = new ObjectMapper();
            List<Data> obj = mapper.readValue(jsonResponse, new TypeReference<>() {});
            List<DataPojo> objPojo = toPojoList(obj);

            int totalElements = objPojo.size();
            int start = (page-1) * size;
            int end = Math.min(start + size, totalElements);

            if (start >= totalElements) {
                throw new NegativeParamException("Page out of range");
            }

            List<DataPojo> content = objPojo.subList(start, end);
            return new PageImpl<>(content, PageRequest.of(page, size), totalElements);
        } catch (Exception e) {
            throw new NegativeParamException(e.getMessage());
        }
    }

    private void validationInput(int page, int size) throws NegativeParamException {
        if (page < 1) {
            throw new NegativeParamException("Page must be greater than zero");
        }
        if (size < 1) {
            throw new NegativeParamException("Page size must be greater than zero");
        }
    }
}
