package com.api.controller;

import com.api.model.Blog;
import com.api.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    BlogService blogService;



    @GetMapping("/list")
    public ModelAndView showList(@PageableDefault(size = 10) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Blog> blogs = blogService.findAll(pageable);
        modelAndView.addObject("blogs", blogs);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());

        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView createBlog(@ModelAttribute(name = "blog") Blog blog) {
        blogService.save(blog);
        return new ModelAndView("redirect:/blogs/list");
    }

    @GetMapping("{id}/edit")
    public ModelAndView showEditForm(@PathVariable Long id, Pageable pageable) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent())
            return new ModelAndView("redirect:/blogs/list");

        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blog", blog.get());

        return modelAndView;
    }

    @PostMapping("{id}/edit")
    public ModelAndView editBlog(@ModelAttribute Blog blog) {
        blogService.save(blog);
        return new ModelAndView("redirect:/blogs/list");
    }

    @GetMapping("{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent())
            return new ModelAndView("redirect:/blogs/list");

        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }

    @PostMapping("{id}/delete")
    public ModelAndView deleteBlog(@PathVariable Long id) {
        blogService.delete(id);
        return new ModelAndView("redirect:/blogs/list");
    }

    @GetMapping("/{id}")
    public ModelAndView viewOneBlog(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent())
            return new ModelAndView("redirect:/blogs/list");

        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("blog", blog.get());
        return modelAndView;
    }


}
