package com.github.wololock;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping
final class FormController {

    @GetMapping
    public String displayForm(Model model) {
        model.addAttribute("form", new Form());
        return "form";
    }

    @PostMapping
    public String processForm(@Valid Form form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        redirectAttributes.addFlashAttribute("form", form);
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String successPage(Model model, @ModelAttribute("form") Form form) {
        model.addAttribute("form", form);
        return "success";
    }
}
