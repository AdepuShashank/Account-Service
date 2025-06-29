package com.Adepu.Account.Service.Service;

import com.Adepu.Account.Service.Feign.TransactionService;
import com.Adepu.Account.Service.Feign.AuthenticationService;
import com.Adepu.Account.Service.Model.UserAccount;
import com.Adepu.Account.Service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    TransactionService accService;
    @Autowired
    AuthenticationService accAuthService;

    private final HandlerExceptionResolver handlerExceptionResolver;
    UserRepository userRepository;


    public UserService(UserRepository userRepository ,HandlerExceptionResolver handlerExceptionResolver) {
        this.userRepository = userRepository;

        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    public List<UserAccount> findAll() {
        return userRepository.findAll();
    }

    public UserAccount findById(Long id){
        Optional<UserAccount> SingleUser = userRepository.findById(id);
        UserAccount us;
        if(SingleUser.isEmpty())
            return null;
        us = SingleUser.get();
        return us;
    }

    public UserAccount UpdateUserDetails(Long id , String firstName, Double balance, Long phoneNumber, int age, String gender) throws Exception
    {
        Optional<UserAccount> SingleUser = userRepository.findById(id);

        if (SingleUser.isEmpty()) {
            throw new Exception("User not found");
        } else {
            UserAccount us = SingleUser.get();

            if (firstName != null) {
                us.setUserName(firstName);
            }


            UserAccount updateduser = us;

            return updateduser;

        }
    }

    public UserAccount CreateAccount(UserAccount userAccount){

        UserAccount us = accAuthService.registerUser(userAccount);



        return us;
    }



    public String login(UserAccount user){
        String token = accAuthService.login(user);
        return token ;
    }


    public UserAccount getBalance(long id, String token){
        String userName = accAuthService.validateToken(token);
        Optional<UserAccount> SingleUser = userRepository.findById(id);



        if (SingleUser.get().getUserName().equals(userName)) {
            UserAccount ac = accService.getAccountBalance(id);
            return ac;
        }
        else{
            System.out.println("Account not found");
            return null;
        }

    }

    public UserAccount depositAmount(long id, UserAccount acc, String token){

        String userName = accAuthService.validateToken(token);
        Optional<UserAccount> SingleUser = userRepository.findById(id);

        if (SingleUser.get().getUserName().equals(userName)) {
            UserAccount ac = accService.deposit(id, acc);
            return ac;
        }
        else{
            System.out.println("Account not found");
            return null;
        }
//        Account ac = accService.deposit(id, acc);
//        return ac;

    }

    public UserAccount withdrawAmount(long id, UserAccount acc,String token) throws Exception {
        String userName = accAuthService.validateToken(token);
        Optional<UserAccount> SingleUser = userRepository.findById(id);

        if (SingleUser.get().getUserName().equals(userName)) {
            UserAccount ac = accService.withDrawAmount(id,acc);
            return ac;
        }
        else{
            System.out.println("Account not found");
            return null;
        }
//        Account ac = accService.withDrawAmount(id,acc);
//        return ac;
    }

    public UserAccount transfer(long id, UserAccount from, long toid,String token){
        String userName = accAuthService.validateToken(token);
        Optional<UserAccount> SingleUser = userRepository.findById(id);

        if (SingleUser.get().getUserName().equals(userName)) {
            UserAccount ac ;
            try {
                ac = accService.transfer(id, from, toid);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ac;
        }
        else{
            System.out.println("Account not found");
            return null;
        }
//        Account ac ;
//        try {
//            ac = accService.transfer(id, from, toid);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return ac;
    }
}
