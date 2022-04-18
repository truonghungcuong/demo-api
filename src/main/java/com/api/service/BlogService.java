package com.api.service;

import com.api.model.Blog;
import com.api.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class BlogService implements IBlogService {
    @Autowired
    IBlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        blog.setModifyDate(getCurrentDateTime());
        return blogRepository.save(blog);
    }

    @Override
    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }


    public String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(now);
    }

}

