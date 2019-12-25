package top.aiteky.onebook.entity;

import java.io.Serializable;

/**
 * tag
 * @author 
 */
public class Tag implements Serializable {
    private Integer id;

    private String name;

    private String link;

    private Boolean hasdone;

    private static final long serialVersionUID = 1L;

    public Tag(){}

    public Tag(String name, String link, Boolean hasdone) {
        this.name = name;
        this.link = link;
        this.hasdone = hasdone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getHasdone() {
        return hasdone;
    }

    public void setHasdone(Boolean hasdone) {
        this.hasdone = hasdone;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", hasdone=" + hasdone +
                '}';
    }
}