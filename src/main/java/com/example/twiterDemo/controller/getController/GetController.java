package com.example.twiterDemo.controller.getController;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryInterface;
import com.example.twiterDemo.dtoService.dTO.PostDTO;
import com.example.twiterDemo.dtoService.twiterDTOService.TwiterMappingService;
import com.example.twiterDemo.dtoService.dTO.TwiterUsersDTO;
import com.example.twiterDemo.objectClasesAndRepository.models.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class GetController implements RepositoryInterface {

    @Autowired
    private TwiterMappingService twiterMappingService;


    @GetMapping("/findWhoFollowYou")
    @ResponseBody
    public List<TwiterUsersDTO> findWhoFollowYou (@RequestParam Long userNameId){

        List<Follow> usersDbList = followRepository.findAllWhoFallowYou(userNameId);
        List<TwiterUsersDTO> userName = new ArrayList<>();
        for (Follow follow : usersDbList) {
            userName.add(twiterMappingService.getById(follow.getUser().getId()));
        }
      return userName;
    }


    @GetMapping("/getUsers")
    @ResponseBody
    public List<TwiterUsersDTO>getAllUsers(){

        return twiterMappingService.getAllTwiterUsers();
    }


    @GetMapping("/serchUsers")
    @ResponseBody
    public Set<TwiterUsersDTO> serchUsers(@RequestParam String findKey){
        return twiterMappingService.serchUsers(findKey);
    }

    @GetMapping("/getUserPosts")
    @ResponseBody
    public Set<PostDTO> getUserPosts (@RequestParam Long userId){
        return twiterMappingService.getOwnPosts(userId);
    }

    @GetMapping("/getUserFollowersPosts")
    @ResponseBody
    public Set<PostDTO> getUserFollowersPosts (@RequestParam Long userId){
        return twiterMappingService.getUserFollowersPosts(userId);
    }
    @GetMapping("/getUserAndFollowersPosts")
    @ResponseBody
    public Set<PostDTO> getUserAndFollowersPosts (@RequestParam Long userId){
        Set<PostDTO> postDTOSet = twiterMappingService.getOwnPosts(userId);
        postDTOSet.addAll(twiterMappingService.getUserFollowersPosts(userId));
        return postDTOSet;
    }

}
