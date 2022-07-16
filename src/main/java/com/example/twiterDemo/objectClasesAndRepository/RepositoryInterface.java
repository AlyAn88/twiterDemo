package com.example.twiterDemo.objectClasesAndRepository;

import com.example.twiterDemo.controller.postController.PostControllerMethods;
import com.example.twiterDemo.objectClasesAndRepository.repository.*;

import org.springframework.beans.factory.annotation.Autowired;

public interface RepositoryInterface {
    @Autowired
    TwiterUsersRepository twiterUsersRepository=new TwiterUsersRepository();
    UserRepository userRepository = new UserRepository();
    ReplyRepository replyRepository = new ReplyRepository();
    PostControllerMethods postControllerMethods = new PostControllerMethods();
    PostRepository postRepository = new PostRepository();
    LikeRepository likeRepository = new LikeRepository();
    FollowRepository followRepository = new FollowRepository();
}
