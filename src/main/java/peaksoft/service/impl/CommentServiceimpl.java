package peaksoft.service.impl;

import peaksoft.dao.CommentDao;
import peaksoft.dao.impl.CommentDaoImpl;
import peaksoft.entities.Comment;
import peaksoft.service.CommentService;

import java.util.List;

public class CommentServiceimpl implements CommentService {
    CommentDao dao = new CommentDaoImpl();
    @Override
    public String saveCommentByBlogPostId(Long id, Comment comment ) {
        return dao.saveCommentByBlogPostId(id, comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return dao.getCommentById(id);
    }

    @Override
    public String updateCommentTextById(Long id, String newComment_text) {
        return dao.updateCommentTextById(id, newComment_text);
    }

    @Override
    public String deleteComment(Long id) {
        return dao.deleteComment(id);
    }

    @Override
    public List<Comment> sortByPublisherDate(String ascOrDesc) {
        return dao.sortByPublisherDate(ascOrDesc);
    }

    @Override
    public String groupCommentsByPost(Long postId) {
        return dao.groupCommentsByPost(postId);
    }
}
