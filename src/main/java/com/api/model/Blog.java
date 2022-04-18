package com.api.model;

import javax.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tittle;

    @Column(columnDefinition = "text")
    private String content;



    @Column(columnDefinition = "DateTime")
    private String modifyDate;


    public String getPreviewContent(int maxSize) {
        if (content.length() < maxSize)
            return content;
        return content.substring(0, maxSize - 3) + "...";
    }

    public Blog() {
    }

    public Blog(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
