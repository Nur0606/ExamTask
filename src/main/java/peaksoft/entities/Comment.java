package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "com_seg")
    @SequenceGenerator(name = "com_seg",sequenceName = "com_seg",allocationSize = 1)
    private Long id;
    private String comment_text;
    private LocalDate publisher_date;
   @OneToMany(mappedBy = "comments",cascade = {
           CascadeType.REFRESH,
           CascadeType.MERGE,
           CascadeType.DETACH})
    private List<BlogPost> blogPost;
    public Comment(String comment_text, LocalDate publisher_date) {
        this.comment_text = comment_text;
        this.publisher_date = publisher_date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment_text='" + comment_text + '\'' +
                ", publisher_date=" + publisher_date +
                '}';
    }
}
