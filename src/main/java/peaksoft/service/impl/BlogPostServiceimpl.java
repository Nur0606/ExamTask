package peaksoft.service.impl;

import peaksoft.dao.BlogPostDao;
import peaksoft.dao.impl.BlogPostDaoimpl;
import peaksoft.entities.BlogPost;
import peaksoft.service.BlogPostService;

import java.util.List;

public class BlogPostServiceimpl implements BlogPostService {
    BlogPostDao dao = new BlogPostDaoimpl();
    @Override
    public String saveBlogPost(BlogPost blogPost) {
        return dao.saveBlogPost(blogPost);
    }

    @Override
    public BlogPost getBlogPostById(Long id) {
        return dao.getBlogPostById(id);
    }

    @Override
    public String updateBlogPostById(Long id, String content) {
        return dao.updateBlogPostById(id, content);
    }

    @Override
    public String deleteBlogPostById(Long id) {
        return dao.deleteBlogPostById(id);
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        return dao.getAllBlogPost();
    }

    @Override
    public List<BlogPost> searchBlogPostByKeyword(Long keyword) {
        return dao.searchBlogPostByKeyword(keyword);
    }
}
