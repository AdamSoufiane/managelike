package com.example.managelike.application.dtos;

import lombok.Data;
import com.example.managelike.application.dtos.LikeAction;
import java.io.Serializable;
import java.util.Objects;

@Data
public class LikeResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long postId;
    private Long userId;
    private LikeAction action;
    private int likesCount;

    public LikeResponse(Long postId, Long userId, LikeAction action, int likesCount) {
        this.postId = postId;
        this.userId = userId;
        this.action = action;
        this.likesCount = likesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LikeResponse that = (LikeResponse) o;

        if (likesCount != that.likesCount) return false;
        if (!Objects.equals(postId, that.postId)) return false;
        if (!Objects.equals(userId, that.userId)) return false;
        return action == that.action;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(postId);
        result = 31 * result + Objects.hashCode(userId);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + likesCount;
        return result;
    }

    @Override
    public String toString() {
        return "LikeResponse{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", action=" + action +
                ", likesCount=" + likesCount +
                '}';
    }
}