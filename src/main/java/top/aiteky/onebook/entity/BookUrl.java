package top.aiteky.onebook.entity;

import java.io.Serializable;

/**
 * book_url
 * @author 
 */
public class BookUrl implements Serializable {
    private Long id;

    private String link;

    private String tag;

    private Boolean hasdone;

    private static final long serialVersionUID = 1L;

    public BookUrl(){}

    public BookUrl(String link, String tag, boolean hasdone) {
        this.link = link;
        this.tag = tag;
        this.hasdone = hasdone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getHasdone() {
        return hasdone;
    }

    public void setHasdone(Boolean hasdone) {
        this.hasdone = hasdone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", link=").append(link);
        sb.append(", tag=").append(tag);
        sb.append(", hasdone=").append(hasdone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}