package com.example.twiterDemo.controller.postController;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryInterface;


import com.example.twiterDemo.objectClasesAndRepository.models.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class PostController implements RepositoryInterface {


    @PostMapping("/createUser")
    public String createUser(@RequestBody TwiterUsers twiterUsers) {
        return postControllerMethods.createAndSaveTwiterUser(twiterUsers);

    }


    @PostMapping("/setReplyToAPost")
    public String setReplyToAPost(@RequestParam Boolean itIsPublic,
                                  @RequestParam Long postIdToReply,
                                  @RequestParam String message,
                                  @RequestParam Long userNameId) {

        Reply newReply = new Reply();
        newReply.setOnlyYou(itIsPublic);

        //Save to generate an id
        Reply replySaved = replyRepository.save(newReply);

        Post postToReply = new Post();

        //Verify if is a userName mentioned in message
        postControllerMethods.ifExistAMention(message, postIdToReply);

        // Find if this post exists
        if (postRepository.findById(postIdToReply).isPresent()) {
            postToReply = postRepository.findById(postIdToReply).get();
        }

        List<Reply> replies = postToReply.getReplies();
        replies.add(replySaved);
        postRepository.save(postToReply);

        //Create and save the reply post
        Post postToSetReply = new Post();
        if (twiterUsersRepository.findById(userNameId).isPresent()) {
            postToSetReply = new Post(message, twiterUsersRepository.findById(userNameId).get().getUser());
        }

        postToSetReply.setReplyToPost(replySaved);
        replySaved.setPost(postToSetReply);

        List<Post> posts = twiterUsersRepository.findById(userNameId).get().getPosts();
        posts.add(postToSetReply);
        TwiterUsers twiterUsersHoSetTheReply = twiterUsersRepository.findById(userNameId).get();
        twiterUsersHoSetTheReply.setPosts(posts);
        twiterUsersRepository.save(twiterUsersHoSetTheReply);

        return " Have a reply to post ( " + postToReply.getMessage() +
                " ) from user " + twiterUsersRepository.findById(userNameId).get().getUserName();
    }

    @PostMapping("/addUserPost")
    public String addUserPost(@RequestParam Long userNameId,
                              @RequestParam String message) {

        TwiterUsers twiterUsers = new TwiterUsers();
        if (twiterUsersRepository.findById(userNameId).isPresent()) {
            twiterUsers = twiterUsersRepository.findById(userNameId).get();
        }
        Post post = new Post(message, twiterUsers.getUser());
        post = postRepository.save(post);

        postControllerMethods.ifExistAMention(message, post.getId());

        twiterUsers.getPosts().add(post);
        twiterUsersRepository.save(twiterUsers);

        return twiterUsers.getUserName() + ": your message is posted successfully";
    }

    @PostMapping("/likeAPost")
    public String likeAPost(@RequestParam Long postIdILike,
                            @RequestParam Long userNameId) {

        Like like = new Like();
        if (userRepository.findById(userNameId).isPresent()){
            like.setUser(userRepository.findById(userNameId).get());
        }
        like = likeRepository.save(like);

        Post postILike = new Post();
        if (postRepository.findById(postIdILike).isPresent()){
            postILike = postRepository.findById(postIdILike).get();
        }
        postILike.getWhoLikeYourPost().add(like);


        List<Post> postsWereILike = new ArrayList<>();
        if (likeRepository.findById(like.getId()).isPresent()){
            postsWereILike = likeRepository.findById(like.getId()).get().getPosts();
        }
        postsWereILike.add(postILike);
        like.setPosts(postsWereILike);
        likeRepository.save(like);

        return like.toString();

    }

    @PostMapping("/followAUser")
    public String followAUser(@RequestParam Long userIdWhoIsFollowd,
                              @RequestParam Long userNameId) {

        if (Objects.equals(userIdWhoIsFollowd, userNameId)) {
            return null;
        }
        Follow follow = new Follow(userRepository.findById(userNameId).get());
        User userHoFollow = userRepository.findById(userNameId).get();
        List<Follow> userFollow = userHoFollow.getUsersIFollow();
        userFollow.add(follow);
        userHoFollow.setUsersIFollow(userFollow);
        follow.setFollowThisUser(userRepository.findById(userIdWhoIsFollowd).get());
        userRepository.save(userHoFollow);

        return "You follow and you start receive "
                +twiterUsersRepository.findById(userIdWhoIsFollowd).get().getUserName()+
                "'s posts";

    }


}
