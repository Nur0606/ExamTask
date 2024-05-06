package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.DatabaseConfig;
import peaksoft.dao.CommentDao;
import peaksoft.entities.BlogPost;
import peaksoft.entities.Comment;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    EntityManagerFactory entityManagerFactory = DatabaseConfig.entityManagerFactory();

    @Override
    public String saveCommentByBlogPostId(Long id,  Comment comment) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            BlogPost blogPost = entityManager.find(BlogPost.class, id);
            blogPost.setComments(comment);
            entityManager.persist(comment);
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
    public Comment getCommentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comment comment = entityManager.find(Comment.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return comment;
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String updateCommentTextById(Long id, String newComment_text) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comment comment = entityManager.find(Comment.class, id);
            comment.setComment_text(newComment_text);
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
    public String deleteComment(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Comment comment = entityManager.find(Comment.class, id);
            entityManager.remove(comment);
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Success deleted!";
        } catch (HibernateException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            return "Error: " + e.getMessage();
        }

    }

    @Override
    public List<Comment> sortByPublisherDate(String ascOrDesc) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
      if (ascOrDesc.equalsIgnoreCase("asc")) {
          List<Comment> comments = entityManager.createQuery("select b.comment_text,b.publisher_date from Comment b order by b.publisher_date",Comment.class).getResultList();
          entityManager.getTransaction().commit();
          entityManager.close();
          return comments;
      }else if (ascOrDesc.equalsIgnoreCase("desc")){
          List<Comment> comments = entityManager.createQuery("select b.comment_text,b.publisher_date from Comment b order by b.publisher_date desc ",Comment.class).getResultList();
          entityManager.getTransaction().commit();
          entityManager.close();
          return comments;
      }else
          throw new RuntimeException("Пиши только назад или в перёд!");
      }catch (HibernateException e ){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println("Error: "+e.getMessage());
        }
        return null;
    }

    @Override
    public String groupCommentsByPost(Long postId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<BlogPost> blogPost;
        try {
            entityManager.getTransaction().begin();
            entityManager.find(BlogPost.class, postId);
            blogPost=entityManager.createQuery("select b.comments from BlogPost  b group by b.comments",BlogPost.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return "Result: "+blogPost;
        }catch (HibernateException e ){
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            return "Error: "+e.getMessage();
        }
    }
}
