package com.nhnacademy.board.Service;


import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.repository.PostRepository;
import com.nhnacademy.board.repository.ReplyRepository;
import com.nhnacademy.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final ReplyRepository replyRepository;


    @Override
    public boolean exists(long id) {
        return postRepository.existsById(id);
    }

    @Override
    public Page<Post> postFindAll(Pageable pageable) {
        return postRepository.getPostAllBy(pageable);
    }

    @Override
    public int postCount() {
        return (int) postRepository.count();
    }

    @Override
    public Post postRegister(String title, String content, String name) {

        Post post = new Post();
//        post.setPostId(0L);
        post.setTitle(title);
        post.setUser(userRepository.findByName(name));
        post.setContent(content);
        post.setPostTime(new Date());
        post.setReplyCount(replyRepository.countByPost_PostId(post.getPostId()));

        return postRepository.save(post);
    }

    @Override
    public Post postModify(Long postId, String title, String content, String name) {
        Post post = new Post();
        post.setPostId(postId);
        post.setTitle(title);
        post.setUser(userRepository.findByName(name));
        post.setContent(content);
        post.setPostTime(new Date());
        post.setReplyCount(replyRepository.countByPost_PostId(postId));
        return postRepository.save(post);
    }

    @Override
    public void postDelete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void increaseReplyCounts(Long postId) {
        postRepository.increaseReplyCount(postId);
    }
    @Override
    public void decreaseReplyCounts(Long postId) {
        postRepository.decreaseReplyCount(postId);
    }

}
