package peaksoft;

import peaksoft.config.DatabaseConfig;
import peaksoft.entities.BlogPost;
import peaksoft.entities.Comment;
import peaksoft.service.BlogPostService;
import peaksoft.service.CommentService;
import peaksoft.service.impl.BlogPostServiceimpl;
import peaksoft.service.impl.CommentServiceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        BlogPostService blogPostService = new BlogPostServiceimpl();
        CommentService commentService = new CommentServiceimpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("""
                    1.Blog post
                    2.Comment
                    """
            );
            switch (scanner.nextInt()) {
                case 1:
                    boolean while1 = true;
                    while (while1) {
                        try {
                            System.out.println("""
                                    1.Add blog post
                                    2.Get blog post by id
                                    3.Update blog post by id
                                    4.Delete blog post by id
                                    5.Get all blog post
                                    6.Search blog post key word
                                    0.Exit
                                    """);
                            switch (scanner.nextInt()) {
                                case 1 ->
                                        System.out.println(blogPostService.saveBlogPost(new BlogPost("Intagram", "true", LocalDate.of(2019, 1, 1))));
                                case 2 -> System.out.println(blogPostService.getBlogPostById(1L));
                                case 3 -> System.out.println(blogPostService.updateBlogPostById(1L, "false"));
                                case 4 -> System.out.println(blogPostService.deleteBlogPostById(2L));
                                case 5 -> System.out.println(blogPostService.getAllBlogPost());
                                case 6 -> System.out.println(blogPostService.searchBlogPostByKeyword(1L));
                                case 0 -> while1 = false;
                                default -> System.out.println("Not-Correct!");
                            }
                        } catch (RuntimeException e) {
                            System.out.println("Error error: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    boolean while2 = true;
                    while (while2) {
                        try {
                        System.out.println("""
                                1.Add comment by blog post id
                                2.Get comment by id
                                3.Update comment by id
                                4.Delete comment by id
                                5.Sort comment by publisher year
                                6.Group comment by post
                                0.Exit
                                """);
                        switch (scanner.nextInt()) {
                            case 1 -> {
                                System.out.println("Enter what blog post to add comment id : ");
                                Long id = scanner.nextLong();
                                System.out.println(commentService.saveCommentByBlogPostId(id, new Comment("Hello", LocalDate.of(2025, 1, 2))));
                            }
                            case 2 -> System.out.println(commentService.getCommentById(1L));
                            case 3 -> System.out.println(commentService.updateCommentTextById(1L, "gudBay"));
                            case 4 -> System.out.println(commentService.deleteComment(1L));
                            case 5 -> System.out.println(commentService.sortByPublisherDate("desc"));
                            case 6 -> System.out.println(commentService.groupCommentsByPost(1L));
                            case 0 -> while2 = false;
                            default -> System.out.println("Not Number !");
                        }
                        }catch (RuntimeException e){
                            System.out.println("Error Oshibka!: "+e.getMessage());
                        }
                    }
                    break;
                default:
                    System.out.println("Kuda idosh etot number net!");
            }
        }
    }
}
