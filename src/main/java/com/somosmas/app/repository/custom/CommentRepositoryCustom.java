package com.somosmas.app.repository.custom;

import com.somosmas.app.model.entity.Comment;
import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> getBodyCommentsSortByOrder();
}
