package com.nhnacademy.board.Service;


import com.nhnacademy.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    boolean exists(long id);
    Page<Post> postFindAll(Pageable pageable);

    int postCount();

    Post postRegister(String title, String content, String name);

    Post postModify(Long postId,String title, String content, String name);

    void postDelete(Long postId);

    void increaseReplyCounts(Long postId);
    void decreaseReplyCounts(Long postId);
}
