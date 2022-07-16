package com.example.twiterDemo.dtoService.dTO;

import java.sql.Timestamp;
import java.util.List;

public class PostDTO implements Comparable<PostDTO> {
    private Long id;
    private String userName;
    private String message;
    private Timestamp timestamp;
    private List<LikeDTO> likePostUserNameList;
    private List<ReplyDTO> postRepliesList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LikeDTO> getLikePostUserNameList() {
        return likePostUserNameList;
    }

    public void setLikePostUserNameList(List<LikeDTO> likePostList) {
        this.likePostUserNameList = likePostList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<ReplyDTO> getPostRepliesList() {
        return postRepliesList;
    }

    public void setPostRepliesList(List<ReplyDTO> replyDTOList) {
        this.postRepliesList = replyDTOList;
    }

    @Override
    public int compareTo(PostDTO o) {
        int i = this.timestamp.compareTo(o.getTimestamp());
        if (o.getPostRepliesList().size() > 0 && i == -1) {
            for (int x = 0; x < o.getPostRepliesList().size(); x++) {
                i = this.timestamp.compareTo(o.getPostRepliesList().get(x).getTimestamp());
                if (i > 0) {
                    break;
                }
            }


        }
        if (i == 1) {
            return i = -1;
        }
        if (i == -1) {
            return i = 1;
        }
        return i;
    }
}
