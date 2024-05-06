package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.BlogPostDao;
import peaksoft.entities.BlogPost;

import java.util.List;

public class BlogPostDaoimpl implements BlogPostDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();

    @Override
    public String saveBlogPost(BlogPost blogPost) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(blogPost);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success saved!";
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public BlogPost getBlogPostById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost = entityManager.find(BlogPost.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return blogPost;
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String updateBlogPostById(Long id, String  content) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost oldBlogPOst = entityManager.find(BlogPost.class, id);
            oldBlogPOst.setContent(content);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success updates!";
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String deleteBlogPostById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost = entityManager.find(BlogPost.class, id);
            entityManager.remove(blogPost);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success deleted";
        }catch (HibernateException e ) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<BlogPost> getAllBlogPost() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<BlogPost> blogPosts = entityManager.createQuery("select b from BlogPost b",BlogPost.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return blogPosts;
        }catch (HibernateException e ) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<BlogPost> searchBlogPostByKeyword(Long keyword) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<BlogPost> blogPosts;
        try {
            entityManager.getTransaction().begin();
            entityManager.find(BlogPost.class,keyword);
            blogPosts = entityManager.createQuery("select b from BlogPost b where b.id = :keyword", BlogPost.class).setParameter("keyword",keyword).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return blogPosts;
        }catch (HibernateException e ) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
