package com.nhnacademy.board.controller;



import com.nhnacademy.board.Service.PostService;
import com.nhnacademy.board.Service.ReplyService;
import com.nhnacademy.board.domain.ReplyRequest;
import com.nhnacademy.board.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final PostService postService;




    @GetMapping("/register")
    public String replyRegister(@ModelAttribute Reply reply
            , @ModelAttribute ReplyRequest replyRegisterRequest,
                                HttpServletRequest req) {
        HttpSession session = req.getSession();
        String writer = (String) session.getAttribute("userLogin");
        replyService.replyRegister(replyRegisterRequest.getPostId(), writer, replyRegisterRequest.getContent());
        postService.increaseReplyCounts(replyRegisterRequest.getPostId());

        return "redirect:/post/" + reply.getPost().getPostId() + "?user==" + writer;
    }

    @GetMapping("/{postId}/{replyId}/update")
    public String replyUpdateForm(@ModelAttribute Reply reply, ModelMap modelMap) {
        modelMap.put("replyId", reply.getReplyId());
        modelMap.put("postId", reply.getPost().getPostId());

        return "replyUpdate";
    }

    @PostMapping("/{postId}/{replyId}/update")
    public String replyUpdate(@ModelAttribute Reply reply
            , @ModelAttribute ReplyRequest replyRegisterRequest,
                              HttpServletRequest req) {
        HttpSession session = req.getSession();
        String writer = (String) session.getAttribute("userLogin");
        replyService.replyModify(reply.getReplyId(), writer, replyRegisterRequest.getContent());
        return "redirect:/post/" + reply.getPost().getPostId();
    }

    @GetMapping("/{postId}/{replyId}/delete")
    public String replyDeleteForm(@ModelAttribute Reply reply,
                                  HttpServletRequest req) {
        HttpSession session = req.getSession();
        String writer = (String) session.getAttribute("userLogin");

        replyService.replyDelete(reply.getReplyId());
        postService.decreaseReplyCounts(reply.getPost().getPostId());
        return "redirect:/post/" + reply.getPost().getPostId();
    }
}
