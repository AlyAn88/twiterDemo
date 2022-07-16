package com.example.twiterDemo.controller.loginController;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryInterface;
import com.example.twiterDemo.objectClasesAndRepository.models.TwiterUsers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController implements RepositoryInterface {

    @GetMapping ("/login")
    @ResponseBody
    public Long login (@RequestBody Login login){
        List<TwiterUsers> twiterUsersList = twiterUsersRepository.findAll();
        if (!twiterUsersList.isEmpty()){
            for (int x=0; x<twiterUsersList.size();x++){
                if (login.getUserName().equals(twiterUsersList.get(x).getUserName())){
                    if (login.getPassword().equals(twiterUsersList.get(x).getUser().getPassword())){
                        return twiterUsersList.get(x).getId();
                    }
                }
            }
        }
        return null;


    }

}
