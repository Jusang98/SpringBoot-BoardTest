package com.nhnacademy.board.Service;


import com.nhnacademy.board.entity.Reply;
import com.nhnacademy.board.repository.PostRepository;
import com.nhnacademy.board.repository.ReplyRepository;
import com.nhnacademy.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("replyService")
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;



    @Override
    public List<Reply> selectReplys(Long postId) {
        return replyRepository.findAllByPost_PostId(postId);
    }

    @Override
    public Reply replyRegister(Long postId, String name, String content) {
        Reply reply = new Reply();
        reply.setReplyId(0L);
        reply.setPost(postRepository.findByPostId(postId));
        reply.setUser(userRepository.findByName(name));
        reply.setContent(content);
        reply.setReplyTime(new Date());

        return replyRepository.save(reply);
    }

    @Override
    public Reply replyModify(Long replyId, String name, String content) {
        Reply reply = new Reply();
        reply.setReplyId(replyId);
        reply.setPost(postRepository.findByPostId(0L));
        reply.setUser(userRepository.findByName(name));
        reply.setContent(content);
        reply.setReplyTime(new Date());

        return replyRepository.save(reply);
    }

    @Override
    public void replyDelete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
