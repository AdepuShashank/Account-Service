package com.Adepu.Account.Service.Feign;

import com.Adepu.Account.Service.Model.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient("TRANSACTION-SERVICE")
public interface TransactionService {
    @GetMapping("/balance/{id}")
    public UserAccount getAccountBalance(@PathVariable("id") Long id);

    @PutMapping("/deposit/{id}")
    public UserAccount deposit(@PathVariable("id") Long id, @RequestBody UserAccount account);

    @PutMapping("/transfer/{id}/to/{otherId}")
    public UserAccount transfer(@PathVariable Long id,@RequestBody UserAccount account, @PathVariable Long otherId) throws Exception;

    @PutMapping("/withDraw/{id}")
    public UserAccount withDrawAmount(@PathVariable Long id, @RequestBody UserAccount account) throws Exception;
}
