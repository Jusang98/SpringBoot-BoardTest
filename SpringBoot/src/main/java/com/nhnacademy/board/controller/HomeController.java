package com.nhnacademy.board.controller;


import com.nhnacademy.board.Service.PostService;
import com.nhnacademy.board.domain.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final PostService postService;




    @GetMapping
    public String homeForm(@RequestParam(value = "page", defaultValue = "1") int page, HttpServletRequest req, Pageable pageable, Model model) {
        HttpSession session = req.getSession();
        String user = (String) session.getAttribute("userLogin");
        model.addAttribute("user", user);
        Pagination pagination = new Pagination(postService.postCount(), page);
        model.addAttribute("postList", postService.postFindAll(pageable));
        model.addAttribute("page", page);
        model.addAttribute("pagination", pagination);
        return "home";
    }
}
