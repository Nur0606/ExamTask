package peaksoft.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.entities.BlogPost;
import peaksoft.entities.Comment;


import java.util.Properties;

public class DatabaseConfig {
    public static Properties properties() {
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5432/exum_task");
        properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, "1234");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "true");
        return properties;
    }
    public static EntityManagerFactory entityManagerFactory(){
        Configuration configuration = new Configuration();
        configuration.addProperties(properties());
        configuration.addAnnotatedClass(BlogPost.class);
        configuration.addAnnotatedClass(Comment.class);

        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);

    }
}
