package com.somosmas.app.model.response;

import java.util.List;

public class ListCommentResponse {
    private List<CommentResponse> comments;

    public ListCommentResponse() {
        
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
    
}
