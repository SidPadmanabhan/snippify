package com.snippify.snippify.controller;

import com.snippify.snippify.service.SnippetService;
import com.snippify.snippify.model.Snippet;
import org.springframework.web.bind.annotation.*;
import java.util.*;



@RestController
// base redirect url
@RequestMapping("/api/snippets")
public class SnippetController {

    private final SnippetService snippetService; 

    //pass in the SAME snippetservice

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    //user wants to post a snippet to database
    @PostMapping
    public Snippet createSnippet(@RequestBody Snippet snippet) {
        return snippetService.createSnippet(snippet);
    }

    //user wants to see all their snippets
    @GetMapping
    public List<Snippet> getAllSnippets() {
        return snippetService.getAllSnippets(); 
    }

    //user wants a specific snippet
    @GetMapping("/{id}")
    public Snippet getSnippetbyId(@PathVariable Long id) {
        return snippetService.getSnippetById(id)
            .orElseThrow(()->new RuntimeException("Snippet not found with id: " + id));
    }

    //user wants to update a specific snippet
    @PutMapping("/{id}")
    public Snippet updateSnippet(@PathVariable Long id, @RequestBody Snippet snippetDetails) {
        return snippetService.updateSnippet(id, snippetDetails);
    }

    //user wants to delete a specific snippet
    @DeleteMapping("/{id}")
    public void deleteSnippet(@PathVariable Long id) {
        snippetService.deleteSnippet(id);
    }


}

