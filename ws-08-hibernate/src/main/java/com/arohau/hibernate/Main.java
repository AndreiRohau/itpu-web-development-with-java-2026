package com.arohau.hibernate;

import com.arohau.hibernate.entity.Comment;
import com.arohau.hibernate.entity.Post;
import com.arohau.hibernate.service.SocialMediaService;
import com.arohau.hibernate.service.SocialMediaServiceImpl;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/*
Add Watchers :
em.createNativeQuery("select * from posts", org.itpu.entity.Post.class).getResultList()
em.createNativeQuery("select * from comments", org.itpu.entity.Comment.class).getResultList()
 */
public class Main {
    public static void main(String[] args) {
//        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("socialMediaPersistenceUnit");
        try (SessionFactory sf = new Configuration().configure().buildSessionFactory();
             Session s = sf.openSession()) {
            // Initialize EntityManagerFactory and EntityManager
            SocialMediaService service = new SocialMediaServiceImpl(s);
            System.out.println("-".repeat(100) + "\n\n" + "-".repeat(100));

            populateDatabase(s, service);

            nPlus1Problem(s);

            deletingBidirectionalAssociation(s, service);

            // check lazy loading
            firstLevelCacheAndDetachedObjects(s, service);

            System.out.println("EXIT");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void nPlus1Problem(EntityManager em) {
        // n+1
        List<Post> posts = em.createNativeQuery("SELECT * FROM Posts", Post.class).getResultList();
        for (Post post : posts) {
            List<Comment> comments = post.getComments();
            System.out.println("AMOUNT OF COMMENTS IS == " + comments.size());
        }
        em.clear();

        System.out.println("-".repeat(100) + "\n\n" + "-".repeat(100));
    }

    // Deleting bidirectional association
    private static void deletingBidirectionalAssociation(EntityManager em, SocialMediaService service) {
        // JPA practical task solution
        service.removeComment(2);
        service.removePost(2);
        em.clear();

        System.out.println("-".repeat(50));

        List<Post> posts = em.createNativeQuery("SELECT * FROM Posts", Post.class).getResultList();
        for (Post post : posts) {
            System.out.println(post + " | comments.size = " + post.getComments().size());
        }

        System.out.println("-".repeat(100) + "\n\n" + "-".repeat(100));
        em.clear();
    }

    private static void firstLevelCacheAndDetachedObjects(EntityManager em, SocialMediaService service) {
        em.getTransaction().begin();

        Post post = em.find(Post.class, 1);
        Post postCopy = em.find(Post.class, 1);

        em.getTransaction().commit();

        Post postById = service.getPostById(1);
        Post postByIdCopy = service.getPostById(1);
        em.clear();
        Post postByIdNewCall = service.getPostById(1);

        em.clear();

        System.out.println("-".repeat(100) + "\n\n" + "-".repeat(100));
    }

    private static void populateDatabase(EntityManager em, SocialMediaService service) {
        Integer postId1 = service.createPost(post -> {
            post.setContent("Post content 1");
            post.setAuthor("Author 1");
        });
        Integer postId2 = service.createPost(post -> {
            post.setContent("Post content 2");
            post.setAuthor("Author 2");
        });

        service.addComment(postId1, comment -> {
            comment.setText("Comment 1");
            comment.setCommenter("Commenter 1");
        });
        service.addComment(postId1, comment -> {
            comment.setText("Comment 2");
            comment.setCommenter("Commenter 2");
        });
        service.addComment(postId2, comment -> {
            comment.setText("Comment 3");
            comment.setCommenter("Commenter 3");
        });
        service.addComment(postId2, comment -> {
            comment.setText("Comment 4");
            comment.setCommenter("Commenter 4");
        });
        em.clear();

        System.out.println("-".repeat(100) + "\n\n" + "-".repeat(100));
        System.out.println("Post id 1 with two comments ids 1, 2");
        System.out.println("Post id 2 with two comments ids 3, 4");
        System.out.println("-".repeat(100) + "\n\n" + "-".repeat(100));

    }
}
