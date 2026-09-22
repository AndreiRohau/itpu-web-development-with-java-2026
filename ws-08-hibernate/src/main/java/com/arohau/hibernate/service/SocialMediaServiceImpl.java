package com.arohau.hibernate.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import com.arohau.hibernate.entity.Comment;
import com.arohau.hibernate.entity.Post;

import java.util.function.Consumer;

public class SocialMediaServiceImpl implements SocialMediaService {

    private final EntityManager em;

    public SocialMediaServiceImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post getPostById(Integer id) {
        return em.find(Post.class, id);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return em.find(Comment.class, id);
    }

    @Override
    public Integer createPost(Consumer<Post> postConfig) {
        try {
            em.getTransaction().begin();

            Post post = new Post();
            postConfig.accept(post);

            em.persist(post); // Persisting the post entity
            em.getTransaction().commit();
            return post.getId();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error creating post", e);
        }
    }

    @Override
    public Integer addComment(Integer postId, Consumer<Comment> commentConfig) {
        try {
            em.getTransaction().begin();

            Post post = em.find(Post.class, postId);
            if (post == null) {
                throw new IllegalArgumentException("Post with ID " + postId + " not found.");
            }

            Comment comment = new Comment();
            commentConfig.accept(comment);
            // set bidirectional mapping
            comment.setPost(post);
            post.getComments().add(comment);

            em.persist(comment);
            em.getTransaction().commit();
            return comment.getId();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error adding comment", e);
        }
    }

    @Override
    public void removePost(Integer postId) {
        try {
            em.getTransaction().begin();

            Post post = em.find(Post.class, postId);
            if (post == null) {
                throw new IllegalArgumentException("Post with ID " + postId + " not found.");
            }

            em.remove(post);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error removing post", e);
        }
    }

    @Override
    public void removeComment(Integer commentId) {
        try {
            em.getTransaction().begin();

            Comment comment = em.find(Comment.class, commentId);
            if (comment == null) {
                throw new IllegalArgumentException("Comment with ID " + commentId + " not found.");
            }
            comment.getPost().getComments().remove(comment); // MUST!!! unlink bidirectional association

            em.remove(comment);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error removing comment", e);
        }
    }
}
