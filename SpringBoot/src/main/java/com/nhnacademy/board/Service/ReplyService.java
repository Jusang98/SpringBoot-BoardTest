package com.nhnacademy.board.Service;


import com.nhnacademy.board.entity.Reply;

import java.util.List;

public interface ReplyService {

    List<Reply> selectReplys(Long postId);
    Reply replyRegister(Long postId,String name, String content);

    Reply replyModify(Long replyId, String name, String content);

    void replyDelete(Long replyId);


}
