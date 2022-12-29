package com.nhnacademy.board.repository;

import com.nhnacademy.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReplyRepository extends JpaRepository<Reply,Long> {
    List<Reply> findAllByPost_PostId(Long postId);

    int countByPost_PostId(Long postId);
}
