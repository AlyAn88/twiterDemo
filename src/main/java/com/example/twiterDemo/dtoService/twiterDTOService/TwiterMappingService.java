package com.example.twiterDemo.dtoService.twiterDTOService;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryInterface;
import com.example.twiterDemo.dtoService.dTO.LikeDTO;
import com.example.twiterDemo.dtoService.dTO.PostDTO;
import com.example.twiterDemo.dtoService.dTO.ReplyDTO;
import com.example.twiterDemo.dtoService.dTO.TwiterUsersDTO;



import com.example.twiterDemo.objectClasesAndRepository.models.*;
import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class TwiterMappingService implements RepositoryInterface {
   // @Autowired
   // private TwiterUsersRepository twiterUsersRepository;

    public List<TwiterUsersDTO> getAllTwiterUsers() {
        return (twiterUsersRepository.findAll())
                .stream().map(this::convertTwiterUsersDataIntoDTO).collect(Collectors.toList());
    }

    public TwiterUsersDTO getById(Long id) {
        return twiterUsersRepository.findById(id).stream().map(this::convertTwiterUsersDataIntoDTO).collect(Collectors.toList()).get(0);
    }

    public Set<TwiterUsersDTO> serchUsers(String findKey) {
        List<TwiterUsersDTO> allUsers = getAllTwiterUsers();
        Set<TwiterUsersDTO> usersFindetByKey = new TreeSet<>();
        for (int x = 0; x < allUsers.size(); x++) {
            if (allUsers.get(x).getUserName().toUpperCase().contains(findKey.toUpperCase())) {
                usersFindetByKey.add(allUsers.get(x));
            }
            if (allUsers.get(x).getFirstName().toUpperCase().contains(findKey.toUpperCase())) {
                usersFindetByKey.add(allUsers.get(x));
            }
            if (allUsers.get(x).getLastName().toUpperCase().contains(findKey.toUpperCase())) {
                usersFindetByKey.add(allUsers.get(x));
            }
        }
        return usersFindetByKey;
    }

    public Set<PostDTO> getOwnPosts(Long userId) {

        TwiterUsers twiterUsers = twiterUsersRepository.findById(userId).get();
        Set<PostDTO> userPosts = new TreeSet<>();

        for (int y = 0; y < twiterUsers.getPosts().size(); y++) {
            if (twiterUsers.getPosts().get(y).getReplyToPost() == null) {
                userPosts.add(convertPostDataIntoDTO(twiterUsers.getPosts().get(y)));
            }
        }

        String query = "select s from Like s where s.user.id=:id";
        TypedQuery<Like> queries = RepositoryClassController.entityManager.createQuery(query, Like.class).setParameter("id", userId);
        if (!queries.getResultList().isEmpty()) {
            List<Post> userLiketPosts = queries.getSingleResult().getPosts();
            userPosts.addAll(userLiketPosts.stream().map(this::convertPostDataIntoDTO).collect(Collectors.toSet()));
        }

        return userPosts;
    }

    public Set<PostDTO> getUserFollowersPosts(Long id) {
        TwiterUsers twiterUsers = twiterUsersRepository.findById(id).get();
        Set<PostDTO> userPosts = new TreeSet<>();

        if (twiterUsers.getUser().getUsersIFollow() != null) {

            for (int x = 0; x < twiterUsers.getUser().getUsersIFollow().size(); x++) {
                List<Post> followersPosts = twiterUsersRepository.findById(twiterUsers.getUser()
                        .getUsersIFollow().get(x).getFollowThisUser().getId()).get().getPosts();

                for (Post followersPost : followersPosts) {

                    if (followersPost.getReplyToPost() == null) {
                        userPosts.add(convertPostDataIntoDTO(followersPost));
                    }
                }
            }
        }
        return userPosts;
    }

    private TwiterUsersDTO convertTwiterUsersDataIntoDTO(TwiterUsers twiterUsersData) {
        TwiterUsersDTO twiterUsersDTO = new TwiterUsersDTO();
        twiterUsersDTO.setUserName(twiterUsersData.getUserName());
        User user = twiterUsersData.getUser();
        twiterUsersDTO.setFirstName(user.getFirstName());
        twiterUsersDTO.setLastName(user.getLastName());
        twiterUsersDTO.setMail(user.getMail());
        return twiterUsersDTO;
    }

    private PostDTO convertPostDataIntoDTO(Post postData) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postDTO.getId());
        postDTO.setUserName(twiterUsersRepository.findById(postData.getUser().getId()).get().getUserName());
        postDTO.setMessage(postData.getMessage());
        postDTO.setTimestamp(postData.getTimestamp());

        List<Like> whoLikeThisPost = postData.getWhoLikeYourPost();
        List<String> userNameWhoLikeThisPost = new ArrayList<>();
        for (int x = 0; x < whoLikeThisPost.size(); x++) {
            userNameWhoLikeThisPost.add(twiterUsersRepository.findById(whoLikeThisPost.get(x).getUser().getId())
                    .get().getUserName());
        }
        postDTO.setLikePostUserNameList(userNameWhoLikeThisPost.stream().map(this::convertLikeDataIntoDTO)
                .collect(Collectors.toList()));

        postDTO.setPostRepliesList(postData.getReplies().stream()
                .map(this::convertReplyDataIntoDTO).collect(Collectors.toList()));

        return postDTO;
    }

    private ReplyDTO convertReplyDataIntoDTO(Reply replyData) {
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setId(replyDTO.getId());
        replyDTO.setUserName(twiterUsersRepository.findById(replyData.getPost().getUser().getId()).get().getUserName());
        replyDTO.setMessage(replyData.getPost().getMessage());
        replyDTO.setTimestamp(replyData.getPost().getTimestamp());
        replyDTO.setOnlyYou(replyData.getOnlyYou());
        replyDTO.setPostRepliesDTOList(replyData.getPost().getReplies().stream()
                .map(this::convertReplyDataIntoDTO).collect(Collectors.toList()));

        List<Like> whoLikeThisPost = replyData.getPost().getWhoLikeYourPost();
        List<String> userNameWhoLikeThisPost = new ArrayList<>();
        for (int x = 0; x < whoLikeThisPost.size(); x++) {
            userNameWhoLikeThisPost.add(twiterUsersRepository.findById(whoLikeThisPost.get(x).getUser().getId())
                    .get().getUserName());
        }
        replyDTO.setLikePostUserNameList(userNameWhoLikeThisPost.stream().map(this::convertLikeDataIntoDTO)
                .collect(Collectors.toList()));

        return replyDTO;
    }

    private LikeDTO convertLikeDataIntoDTO(String userName) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setUserName(userName);

        return likeDTO;
    }

}
