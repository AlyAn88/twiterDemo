package com.example.twiterDemo;

import com.example.twiterDemo.models.*;
import com.example.twiterDemo.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

    @PostMapping("/createUser")
    String creatUser(@RequestBody TwiterUsers twiterUsers) {

        TwiterUsersRepository twiterUsersRepository = new TwiterUsersRepository();
        User newUser = new User(twiterUsers.getUser().getFirstName(),
                twiterUsers.getUser().getLastName(),
                twiterUsers.getUser().getMail(),
                twiterUsers.getUser().getPassword());
        TwiterUsers newTwiterUsers = new TwiterUsers(twiterUsers.getUserName(), newUser);

        if (twiterUsersRepository.save(newTwiterUsers) != null) {
            return "Your have registered user name " + twiterUsers.getUserName();
        }
        return "Your user name " + newTwiterUsers.getUserName() + newUser + " is already taken ";
    }

    @PostMapping("/addUserPost")
    String addUserPost(@RequestParam Long userNameId,
                       @RequestParam String message) {
        TwiterUsersRepository twiterUsersRepository = new TwiterUsersRepository();

        TwiterUsers twiterUsers = twiterUsersRepository.findById(userNameId).get();
        twiterUsers.getPosts().add(new Post(message, twiterUsers.getUser()));
        twiterUsersRepository.save(twiterUsers);

        return twiterUsers.getUserName() + ": your message is posted succesfuly";

    }

    @PostMapping("/setReplyToAPost")
    String setReplyToAPost(@RequestParam String message,
                           @RequestParam Boolean itIsPublic,
                           @RequestParam Long postIdToReply,
                           @RequestParam Long userNameIdHoSetTheReply) {
        TwiterUsersRepository twiterUsersRepository = new TwiterUsersRepository();
        PostRepository postRepository = new PostRepository();
        ReplyRepository replyRepository = new ReplyRepository();

        Reply newReply = new Reply();
        newReply.setOnlyYou(itIsPublic);
        Reply replySaved = replyRepository.save(newReply);

        Post postToReply = postRepository.findById(postIdToReply).get();
        Set<Reply> replies = postToReply.getReplies();
        replies.add(replySaved);
        postRepository.save(postToReply);

        Post postToSetReply = new Post(message, twiterUsersRepository.findById(userNameIdHoSetTheReply).get().getUser());
        postToSetReply.setReply(replySaved);
        replySaved.setPost(postToSetReply);

       Set<Post> posts = twiterUsersRepository.findById(userNameIdHoSetTheReply).get().getPosts();
        posts.add(postToSetReply);
        TwiterUsers twiterUsersHoSetTheReply = twiterUsersRepository.findById(userNameIdHoSetTheReply).get();
        twiterUsersHoSetTheReply.setPosts(posts);
        twiterUsersRepository.save(twiterUsersHoSetTheReply);


      return " Have a reply to post ( " + postToReply.getMessage() +
               " ) from user " + twiterUsersRepository.findById(userNameIdHoSetTheReply).get().getUserName();
    }
    @PostMapping("/mentionedToAPost")
    String mentionedToAPost (@RequestParam Long postIdWereWasMentioned,
                             @RequestParam Long userIdWhoIsMentioned){
        PostRepository postRepository = new PostRepository();
        UserRepository userRepository = new UserRepository();
        MentionRepository mentionRepository = new MentionRepository();

        Mention mention = new Mention(userRepository.findById(userIdWhoIsMentioned).get());
        mention = mentionRepository.save(mention);

       Post postWereWasMentioned = postRepository.findById(postIdWereWasMentioned).get();
        Set<Post> postsWereWasMentioned =mentionRepository.findById(mention.getId()).get().getPosts();
        postsWereWasMentioned.add(postWereWasMentioned);
        mention.setPosts(postsWereWasMentioned);
        mentionRepository.save(mention);

        return mention.toString();

    }
    @PostMapping("/likeAPost")
    String likeAPost (@RequestParam Long postIdILike,
                      @RequestParam Long userIdWhoLike){
        PostRepository postRepository = new PostRepository();
        UserRepository userRepository = new UserRepository();
        LikeRepository likeRepository = new LikeRepository();

        Like like = new Like(userRepository.findById(userIdWhoLike).get());
        like = likeRepository.save(like);


        Post postILike = postRepository.findById(postIdILike).get();
        Set<Post> postsWereILike =likeRepository.findById(like.getId()).get().getPosts();
        postsWereILike.add(postILike);
        like.setPosts(postsWereILike);
        likeRepository.save(like);

        return like.toString();

    }



}
