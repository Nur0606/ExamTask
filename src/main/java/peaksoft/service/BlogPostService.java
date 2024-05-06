package peaksoft.service;

import peaksoft.entities.BlogPost;

import java.util.List;

public interface BlogPostService {
    String saveBlogPost(BlogPost blogPost);
    BlogPost getBlogPostById(Long id);
    String updateBlogPostById(Long id , String content);
    String deleteBlogPostById(Long id);
    List<BlogPost> getAllBlogPost();
    List<BlogPost>  searchBlogPostByKeyword ( Long keyword);
}
