package com.dovi.studentapi.feignClients;

import com.dovi.studentapi.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ADDRESS-SERVICE/api/addresses")
public interface AddressFeignClient {

    @GetMapping("/{id}")
    AddressDTO getAddressById(@PathVariable Long id);
}
