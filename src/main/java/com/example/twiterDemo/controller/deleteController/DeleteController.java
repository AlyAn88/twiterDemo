package com.example.twiterDemo.controller.deleteController;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryInterface;
import com.example.twiterDemo.objectClasesAndRepository.models.Follow;
import com.example.twiterDemo.objectClasesAndRepository.models.Post;
import com.example.twiterDemo.objectClasesAndRepository.models.TwiterUsers;
import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Transactional
@RestController
public class DeleteController implements RepositoryInterface {

    @Transactional
    @DeleteMapping("/removeUser")
    public String removeUser(@RequestParam Long userId) {
        if (twiterUsersRepository.findById(userId).isPresent()) {
            TwiterUsers twiterUsers = twiterUsersRepository.findById(userId).get();
            if (!twiterUsers.getPosts().isEmpty()) {
                for (Post post : twiterUsers.getPosts()) {
                    Long id = post.getId();
                    deletePost(id);
                }
            }
            String query = "delete from Follow s where s.followThisUser.id = :id";
            RepositoryClassController.entityManager.createNativeQuery(query).setParameter("id",userId);

            twiterUsersRepository.delete(twiterUsers);
            return "Your user and posts of: "+twiterUsers.getUserName()+" is deleted";
        }
        return "We can't find your user";
    }

    @DeleteMapping("/deletePost")
    public String deletePost(@RequestParam Long postId) {
        if (postRepository.findById(postId).isPresent()) {
            Post post = postRepository.findById(postId).get();
            if (!post.getReplies().isEmpty()) {
                for (int x = 0; x < post.getReplies().size(); x++) {
                    deleteReply(post.getReplies().get(x).getId(), postId);
                }
            }
            postRepository.delete(post);
            return "Post is deleted";
        }
        return "We can't find your post";
    }

    @DeleteMapping("/deleteReply")
    public String deleteReply(@RequestParam Long replyId, @RequestParam Long replyToPostId) {
        if(postRepository.findById(replyToPostId).isPresent()) {
            Post post = postRepository.findById(replyToPostId).get();
            post.getReplies().remove(replyRepository.findById(replyId).get());
            deletePost(replyRepository.findById(replyId).get().getPost().getId());
            replyRepository.delete(replyRepository.findById(replyId).get());
            return "The reply is remove";
        }
        return "We can't find your reply";
    }

    @DeleteMapping("/unfollow")
    public String unfollow(@RequestParam Long followId) {
        if (followRepository.findById(followId).isPresent()) {
            Follow follow = followRepository.findById(followId).get();
            followRepository.delete(follow);
            TwiterUsers twiterUsers = twiterUsersRepository.findById(follow.getUser().getId()).get();
            twiterUsers.getUser().getUsersIFollow().remove(follow);
            twiterUsersRepository.save(twiterUsers);
            return "You no longer receve posts from "+twiterUsersRepository.findById(follow.getFollowThisUser().getId()).get().getUserName();
        }
        return "You don't have this follower";
    }
}
