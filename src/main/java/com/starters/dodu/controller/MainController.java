package com.starters.dodu.controller;

import com.starters.dodu.dto.ApplyFormDTO;
import com.starters.dodu.dto.CategoryDTO;
import com.starters.dodu.dto.MenteeDTO;
import com.starters.dodu.dto.MentorDTO;
import com.starters.dodu.service.CategoryService;
import com.starters.dodu.service.MenteeService;
import com.starters.dodu.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final CategoryService categoryService;
    private final MenteeService menteeService;
    private final MentorService mentorService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "0", required = false) Long categoryId) {
        MenteeDTO mentee = menteeService.findById(1l);
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("mentee", mentee);
        model.addAttribute("categories", categories);
        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/applyRequest/{id}")
    public String getApplyForm(@PathVariable String id, Model model) {
        ApplyFormDTO.GetApplyForm applyFormDTO = mentorService.getApplyForm(id);
        model.addAttribute("mentorData", applyFormDTO);
        return "offertomentor";
    }

    @GetMapping("/offertomentor")
    public String offertomentor() {
        return "offertomentor";
    }

    @GetMapping("/admin")
    public String admintest() {
        return "admintest";  
    }

    @GetMapping("/menteechecked")
    public String menteechecked() {
        return "menteechecked";
    }

    @GetMapping("/mymentoring")
    public String mymentoring() {
        return "mymentoring";
    }

    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }

}
