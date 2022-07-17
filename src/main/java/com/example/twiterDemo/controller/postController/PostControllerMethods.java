package com.example.twiterDemo.controller.postController;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.Mention;
import com.example.twiterDemo.objectClasesAndRepository.models.Post;
import com.example.twiterDemo.objectClasesAndRepository.models.TwiterUsers;
import com.example.twiterDemo.objectClasesAndRepository.repository.*;


import javax.persistence.TypedQuery;
import java.util.List;

public class PostControllerMethods implements RepositoryClassController {

    public String createAndSaveTwiterUser(TwiterUsers twiterUsers) {
        TwiterUsersRepository twiterUsersRepository = new TwiterUsersRepository();

        String query = "select s from TwiterUsers s";
        TypedQuery<TwiterUsers> queries = entityManager.createQuery(query, TwiterUsers.class);
        List<TwiterUsers> twiterUsersDbList = queries.getResultList();

        for (int x = 0; x < twiterUsersDbList.size(); x++) {
            if (twiterUsersDbList.get(x).getUserName().equals(twiterUsers.getUserName())) {
                return "Your user name " + twiterUsers.getUserName() + " is already taken ";
            }
        }
        TwiterUsers twiterUsers1 = new TwiterUsers();
        twiterUsers1.setUserName(twiterUsers.getUserName());
        twiterUsers1 = twiterUsersRepository.save(twiterUsers1);
        twiterUsers.setId(twiterUsers1.getId());
        twiterUsers.getUser().setId(twiterUsers1.getId());
        twiterUsersRepository.save(twiterUsers);

        return twiterUsers.getUserName() + " Your user was created successfully";

    }

    public void ifExistAMention(String message, Long postId) {
        TwiterUsersRepository twiterUsersRepository = new TwiterUsersRepository();
        List<TwiterUsers> twiterUsersDBList = twiterUsersRepository.findAll();
        for (int x = 0; x < twiterUsersDBList.size(); x++) {
            if (message.contains("@" + twiterUsersDBList.get(x).getUserName())) {
                String[] parts = message.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("@" + twiterUsersDBList.get(x).getUserName())) {
                        mentionedToAPost(postId, twiterUsersDBList.get(x).getUser().getId());
                    }
                }
            }
        }

    }

    public String mentionedToAPost(Long postIdWereWasMentioned,
                                   Long userIdWhoIsMentioned) {

        UserRepository userRepository = new UserRepository();
        MentionRepository mentionRepository = new MentionRepository();
        Mention mention = new Mention();
        if (userRepository.findById(userIdWhoIsMentioned).isPresent()) {
            mention.setUser(userRepository.findById(userIdWhoIsMentioned).get());
        } else {
            return "This user doesn't exist";
        }
        PostRepository postRepository = new PostRepository();

        mention = mentionRepository.save(mention);

        Post postWereWasMentioned = postRepository.findById(postIdWereWasMentioned).get();
        List<Post> postsWereWasMentioned = mentionRepository.findById(mention.getId()).get().getPosts();
        postsWereWasMentioned.add(postWereWasMentioned);
        mention.setPosts(postsWereWasMentioned);
        mentionRepository.save(mention);

        return mention.toString();

    }


}
