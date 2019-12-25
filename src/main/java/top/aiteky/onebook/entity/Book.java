package top.aiteky.onebook.entity;

import java.io.Serializable;

/**
 * book
 * @author 
 */
public class Book implements Serializable {
    private Long id;

    private String author;

    private String binding;

    private String imprint;

    private String isbn;

    private Long number;

    private String original;

    private Integer pages;

    private String press;

    private Double price;

    private Double score;

    private String series;

    private String tag;

    private String title;

    private String translator;

    private String img;

    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getImprint() {
        return imprint;
    }

    public void setImprint(String imprint) {
        this.imprint = imprint;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Book other = (Book) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getBinding() == null ? other.getBinding() == null : this.getBinding().equals(other.getBinding()))
            && (this.getImprint() == null ? other.getImprint() == null : this.getImprint().equals(other.getImprint()))
            && (this.getIsbn() == null ? other.getIsbn() == null : this.getIsbn().equals(other.getIsbn()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getOriginal() == null ? other.getOriginal() == null : this.getOriginal().equals(other.getOriginal()))
            && (this.getPages() == null ? other.getPages() == null : this.getPages().equals(other.getPages()))
            && (this.getPress() == null ? other.getPress() == null : this.getPress().equals(other.getPress()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getSeries() == null ? other.getSeries() == null : this.getSeries().equals(other.getSeries()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getTranslator() == null ? other.getTranslator() == null : this.getTranslator().equals(other.getTranslator()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getBinding() == null) ? 0 : getBinding().hashCode());
        result = prime * result + ((getImprint() == null) ? 0 : getImprint().hashCode());
        result = prime * result + ((getIsbn() == null) ? 0 : getIsbn().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getOriginal() == null) ? 0 : getOriginal().hashCode());
        result = prime * result + ((getPages() == null) ? 0 : getPages().hashCode());
        result = prime * result + ((getPress() == null) ? 0 : getPress().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getSeries() == null) ? 0 : getSeries().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getTranslator() == null) ? 0 : getTranslator().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", author=").append(author);
        sb.append(", binding=").append(binding);
        sb.append(", imprint=").append(imprint);
        sb.append(", isbn=").append(isbn);
        sb.append(", number=").append(number);
        sb.append(", original=").append(original);
        sb.append(", pages=").append(pages);
        sb.append(", press=").append(press);
        sb.append(", price=").append(price);
        sb.append(", score=").append(score);
        sb.append(", series=").append(series);
        sb.append(", tag=").append(tag);
        sb.append(", title=").append(title);
        sb.append(", translator=").append(translator);
        sb.append(", img=").append(img);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}