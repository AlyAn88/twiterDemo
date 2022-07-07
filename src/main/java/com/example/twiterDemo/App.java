package com.example.twiterDemo;

import com.example.twiterDemo.models.TwiterUsers;
import com.example.twiterDemo.models.Post;
import com.example.twiterDemo.models.Reply;
import com.example.twiterDemo.models.User;
import com.example.twiterDemo.repository.TwiterUsersRepository;
import com.example.twiterDemo.repository.PostRepository;
import com.example.twiterDemo.repository.ReplyRepository;

import java.util.Set;
import java.util.TreeSet;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {


        TwiterUsersRepository twiterUsersRepository = new TwiterUsersRepository();
        PostRepository postRepository = new PostRepository();
        ReplyRepository replyRepository = new ReplyRepository();



       // Post post = new Post("oare va merge");
       // Post post1 = new Post("oare cine va merge");
        //Post post2 = new Post("merge");

        User alyan = new User("Jipa", "Alex", "al3xyan@gmail.com", "123");
        User alyan1 = new User("Jipa1", "Alex1", "al3xyan@gmail.com", "123");

        Set<Post> posts = new TreeSet<>();
        Set<Post> posts1 = new TreeSet<>();
        Set<Reply> replies = new TreeSet<>();

        TwiterUsers twiterUsers = new TwiterUsers("Alyan", alyan);
        TwiterUsers twiterUsers1 = new TwiterUsers("Alyan1",alyan1);

        //post.setUser(alyan);
      //posts.add(post);
       //twiterUsers.setPosts(posts);
        twiterUsersRepository.save(twiterUsers);






        System.out.println();
       /* System.out.println(alyan);
        System.out.println(post);
        System.out.println(post1);
        System.out.println();*/
        twiterUsersRepository.close();
        postRepository.close();


        // twiterUsers.setUser(alyan, "Alyan");
        // users.save(alyan);
        // users.add(alyan, "Alyan");

        //System.out.println(users.findAll());
        // System.out.println();
        // users.close();

    }
}
