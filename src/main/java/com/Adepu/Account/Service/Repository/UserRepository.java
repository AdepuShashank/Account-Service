package com.Adepu.Account.Service.Repository;

import com.Adepu.Account.Service.Model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserAccount,Long> {

}
