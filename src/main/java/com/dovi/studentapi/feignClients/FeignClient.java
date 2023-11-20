package com.dovi.studentapi.feignClients;

import com.dovi.studentapi.dto.AddressDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name = "api-gateway")
public interface FeignClient {

    @GetMapping("address-service/api/addresses/{id}")
    AddressDTO getAddressById(@PathVariable Long id);
}
