package com.nhnacademy.board.controller;



import com.nhnacademy.board.Service.PostService;
import com.nhnacademy.board.domain.PostRegisterRequest;
import com.nhnacademy.board.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class PostRegisterController {
    private final PostService postService;



    @GetMapping
    public String PostRegisterForm() {
        return "postRegister";
    }

    @PostMapping
    public ModelAndView registerPost(@ModelAttribute PostRegisterRequest postRegisterRequest,
                                     HttpServletRequest req, Model model) throws IOException {
        HttpSession session = req.getSession();
        String userId = (String) session.getAttribute("userLogin");
        Post post = postService.postRegister(postRegisterRequest.getTitle(),
                postRegisterRequest.getContent(), userId);


        ModelAndView mav = new ModelAndView("redirect:/home");

        mav.addObject("post", post);

        return mav;
    }
}
