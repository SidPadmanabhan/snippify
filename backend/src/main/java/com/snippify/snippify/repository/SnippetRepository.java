package com.snippify.snippify.repository;

//interface

import com.snippify.snippify.model.Snippet;
// standard database operations from jpa repository
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetRepository extends JpaRepository <Snippet, Long> {
    
}
