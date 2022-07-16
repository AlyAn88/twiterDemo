package com.example.twiterDemo.dtoService.dTO;

import java.sql.Timestamp;
import java.util.List;

public class ReplyDTO {
    private Long id;
    private String userName;
    private String message;
    private Timestamp timestamp;
    private Boolean onlyYou;
    private List<LikeDTO> likePostUserNameList;
    private List<ReplyDTO> postRepliesDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LikeDTO> getLikePostUserNameList() {
        return likePostUserNameList;
    }

    public void setLikePostUserNameList(List<LikeDTO> likePostUserNameList) {
        this.likePostUserNameList = likePostUserNameList;
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

    public Boolean getOnlyYou() {
        return onlyYou;
    }

    public void setOnlyYou(Boolean onlyYou) {
        this.onlyYou = onlyYou;
    }

    public List<ReplyDTO> getPostRepliesDTOList() {
        return postRepliesDTOList;
    }

    public void setPostRepliesDTOList(List<ReplyDTO> postRepliesDTOList) {
        this.postRepliesDTOList = postRepliesDTOList;
    }

}
