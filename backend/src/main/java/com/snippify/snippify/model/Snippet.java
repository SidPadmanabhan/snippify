// snippet should have fields id, title, code, language, description
package com.snippify.snippify.model;
import jakarta.persistence.*;

@Entity
@Table(name = "snippets")
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String code;


    private String language;

    @Column(columnDefinition = "TEXT")
    private String description;
    
    public Snippet() {
        //
    }

    public Snippet(Long userId, String title, String code, String language, String description) 
    { 
        this.userId = userId;
        this.title = title; 
        this.code = code;
        this.language = language;
        this.description = description;
    }

    //getters
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId; 
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getLanguage() {
        return language;
    }

    public String getDescription() {
        return description;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId; 
    }

    public void setTitle(String title) {
       this.title = title;
    }

    public void setCode(String code) {
        this.code =  code;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDescription(String description ) {
        this.description = description;
    }


}
