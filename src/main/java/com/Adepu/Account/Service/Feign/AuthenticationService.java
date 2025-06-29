package com.Adepu.Account.Service.Feign;

import com.Adepu.Account.Service.Model.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("AUTHORIZATION-SERVICE")
public interface AuthenticationService {
    @PostMapping("/createaccount")
    public UserAccount registerUser(@RequestBody UserAccount user);

    @PostMapping("/login")
    public String login(@RequestBody UserAccount user);

    @PostMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String authHeader);
}
