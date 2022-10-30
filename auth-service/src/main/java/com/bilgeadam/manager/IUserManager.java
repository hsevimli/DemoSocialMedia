package com.bilgeadam.manager;

import com.bilgeadam.dto.request.ActivateRequestDto;
import com.bilgeadam.dto.request.NewCreateUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", decode404 = true, url = "http://localhost:8086/user")
public interface IUserManager {
    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody NewCreateUserDto dto);

    @PostMapping("/activate")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateRequestDto dto);
}
