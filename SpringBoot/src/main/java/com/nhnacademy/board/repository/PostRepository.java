package com.nhnacademy.board.repository;


import com.nhnacademy.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Post p set p.replyCount=:replyCount+1 where p.postId=:postId")
    void increaseReplyCount(@Param("postId")Long postId);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Post p set p.replyCount=:replyCount-1 where p.postId=:postId")
    void decreaseReplyCount(@Param("postId")Long postId);
    Page<Post> getPostAllBy(Pageable pageable);

}
