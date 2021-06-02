package com.cachetest.bankapplication.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cachetest.bankapplication.model.UserAccount;
import com.cachetest.bankapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Map<String, UserAccount> accountMap;

    @GetMapping(path = { "/get/{accountNumber}" })
    public UserAccount getUser(@PathVariable("accountNumber") String accountNumber) {
        //first check if accountMap has the userAccount details, if yes then return it. Else fetch it from database.
        UserAccount userAccount = (accountMap.get(accountNumber) != null) ? accountMap.get(accountNumber)
                : userRepository.findByAccountNumber(accountNumber);
        return userAccount;

    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserAccount user) {
        //save user account in cache
        accountMap.put(user.getAccountNumber(), user);
        userRepository.save(user);
    }

    @DeleteMapping(path = { "/{accountNumber}" })
    public UserAccount deleteUser(@PathVariable("accountNumber") String accountNumber) {
        //remove from both cache and database
        accountMap.remove(accountNumber);
        return userRepository.deleteByAccountNumber(accountNumber);
    }

}
