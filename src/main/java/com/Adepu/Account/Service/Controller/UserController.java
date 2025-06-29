package com.Adepu.Account.Service.Controller;

import com.Adepu.Account.Service.Model.UserAccount;
import com.Adepu.Account.Service.Service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createAccount")
    public UserAccount CreateAccount(@RequestBody UserAccount userAccount){
        UserAccount us = userService.CreateAccount(userAccount);
        return us;
    }
    @PostMapping("/login")
    public String login(@RequestBody UserAccount user) throws Exception{
        String loginUser = userService.login(user);
        return loginUser;
    }


    @GetMapping("/balance/{id}")
    public UserAccount getBalance(@PathVariable("id") long id,@RequestHeader("Authorization") String token){
        UserAccount ac = userService.getBalance(id,token);
        return ac;
    }

    @PutMapping("/deposit/{id}")
    public UserAccount updateBalance(@PathVariable("id") long id,@RequestBody UserAccount account,@RequestHeader("Authorization") String token){
        UserAccount ac = userService.depositAmount(id,account,token);
        return ac;
    }

    @PutMapping("/transfer/{id}/to/{otherId}")
    public UserAccount transfer(@PathVariable("id")long id,@RequestBody UserAccount account, @PathVariable("toId") long toID,@RequestHeader("Authorization") String token){
        UserAccount ac = userService.transfer(id,account,toID,token);
        return ac;
    }

    @PutMapping("/withDraw/{id}")
    public UserAccount withDraw(@PathVariable("id") long id, @RequestBody UserAccount account,@RequestHeader("Authorization") String token) throws Exception {
        UserAccount ac = userService.withdrawAmount(id,account,token);
        return ac;
    }
}
