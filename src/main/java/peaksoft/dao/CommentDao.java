package peaksoft.dao;

import peaksoft.entities.Comment;

import java.util.List;

public interface CommentDao {
    String saveCommentByBlogPostId(Long id, Comment comment);
    Comment getCommentById(Long id);
    String updateCommentTextById(Long id , String newComment_text);
    String deleteComment(Long id);
    List<Comment> sortByPublisherDate(String ascOrDesc);
    String groupCommentsByPost(Long postId);
}
