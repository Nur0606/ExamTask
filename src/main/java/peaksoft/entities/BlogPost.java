package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "blog_seg")
    @SequenceGenerator(name = "blog_seg",sequenceName = "blog_seg",allocationSize = 1)
    private Long id;
    private String title;
    private String content;
    private LocalDate publisher_date;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.REMOVE})
    private Comment comments;

    public BlogPost(String title, String content, LocalDate publisher_date) {
        this.title = title;
        this.content = content;
        this.publisher_date = publisher_date;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
               ", publisher_date=" + publisher_date +
               '}';
    }
}
