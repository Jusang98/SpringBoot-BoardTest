package com.nhnacademy.board.controller;



import com.nhnacademy.board.Service.PostService;
import com.nhnacademy.board.Service.ReplyService;
import com.nhnacademy.board.domain.PostRegisterRequest;
import com.nhnacademy.board.entity.Post;
import com.nhnacademy.board.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final ReplyService replyService;



//    @ModelAttribute("post")
//    public Post getPost(@PathVariable("postId") Long postId) {
//        Post post = postService.selectPost(postId);
//        if (Objects.isNull(post)) {
//            throw new PostNotFoundException();
//        }
//        return post;
//    }

    @GetMapping("/{postId}")
    public String viewPost(@ModelAttribute("post") Post post,
                           @ModelAttribute("reply") Reply reply, HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("userLogin");
        model.addAttribute("user", user);
        model.addAttribute("replyList", replyService.selectReplys(post.getPostId()));
        model.addAttribute("post", post);

        return "postView";
    }

    @GetMapping("/{postId}/modify")
    public String postModifyForm() {
        return "postModify";
    }

    @PostMapping("/{postId}/modify")
    public String postmodify(@ModelAttribute Post post,
                             @ModelAttribute PostRegisterRequest postRegisterRequest,
                             HttpServletRequest req,
                             Model model) {

        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userLogin");
        postService.postModify(post.getPostId(),postRegisterRequest.getTitle(),postRegisterRequest.getContent(),userId);

        return "postView";
    }

    @GetMapping("/{postId}/delete")
    public String postDeleteForm(@ModelAttribute Post post) {
        postService.postDelete(post.getPostId());
        return "redirect:/home";
    }
}
