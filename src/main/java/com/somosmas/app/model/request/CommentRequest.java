package com.somosmas.app.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentRequest {

    @NotNull(message = "User ID cannot be null.")
	private long idUser;
	
    @NotEmpty(message = "Body cannot be empty")
    @NotNull(message = "Body cannot be null.")
	private String body;

    @NotNull(message = "Post ID cannot be null.")
	private long idPost;

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getIdPost() {
		return idPost;
	}

	public void setIdPost(long idPost) {
		this.idPost = idPost;
	}

}
