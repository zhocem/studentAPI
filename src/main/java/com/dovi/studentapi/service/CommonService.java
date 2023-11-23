package com.dovi.studentapi.service;

import com.dovi.studentapi.dto.AddressDTO;
import com.dovi.studentapi.feignClients.FeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    Logger logger = LoggerFactory.getLogger(CommonService.class);
    long count = 0;

    @Autowired
    FeignClient feignClient;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressDTO getAddressById(long addressId) {
        logger.info("count = " + count);
        count++;
        return feignClient.getAddressById(addressId);
    }

    public AddressDTO fallbackGetAddressById(long addressId, Throwable throwable) {
        logger.error("Error: " + throwable);
        return new AddressDTO();
    }
}
