package com.snippify.snippify.service;
import com.snippify.snippify.model.Snippet;
// import the repository
import com.snippify.snippify.repository.SnippetRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

//class w/ methods

@Service
public class SnippetService {
    
    private final SnippetRepository snippetRepository;

    public SnippetService(SnippetRepository snippetRepository) { 
        this.snippetRepository = snippetRepository;
    }
    
    //create new snippet
    public Snippet createSnippet(Snippet snippet) {
        // title can't be null
        if (snippet.getTitle() == null || snippet.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required"); 
        }
        // code can't be null
        if (snippet.getCode() == null || snippet.getCode().isEmpty()) {
            throw new IllegalArgumentException("Code is required");
        }
        
        //saves snippet and repo converts snippet to sql and saves it and returns it w a unique id
        return snippetRepository.save(snippet);
    }

    //get all snippets
    public List<Snippet> getAllSnippets() {
        return snippetRepository.findAll();
    } 

    //get a specific snippet
    public Optional<Snippet> getSnippetById(Long id) {
        return snippetRepository.findById(id);
    }
    
    //update an existing method
    public Snippet updateSnippet(Long id, Snippet snippetDetails) {
        //make sure its valid
        Snippet snippet = snippetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Snippet not found with id: " + id));
        // set new fields to the snippet
        snippet.setTitle(snippetDetails.getTitle());
        snippet.setCode(snippetDetails.getCode());
        snippet.setLanguage(snippetDetails.getLanguage());
        snippet.setDescription(snippetDetails.getDescription());

        return snippetRepository.save(snippet); 
    }

    //delete a Snippet
    public void deleteSnippet(Long id) {
        snippetRepository.deleteById(id);
    }

}
