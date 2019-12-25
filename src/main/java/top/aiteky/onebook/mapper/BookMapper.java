package top.aiteky.onebook.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.aiteky.onebook.entity.Book;

import java.util.List;

public interface BookMapper {
    @Insert("INSERT INTO book(`id`, `author`, `binding`, `imprint`, `isbn`, `number`, `original`, `pages`, `press`, `price`, `score`, `series`, `title`, `translator`, `img`, `content`) " +
            "VALUES (${id}, #{author}, #{binding}, #{imprint}, #{isbn}, ${number}, #{original}, ${pages}, #{press}, ${price}, ${score}, #{series}, #{title}, #{translator}, #{img}, #{content})")
    int insert(Book record);

    @Select("select count(id) FROM book where id = ${id}")
    Boolean exist(@Param("id") Long id);

    @Select("SELECT * FROM book AS t1 JOIN (SELECT ROUND(RAND() * (SELECT MAX(id) FROM book)) AS id) AS t2 WHERE t1.id >= t2.id and t1.score >= 9  ORDER BY t1.id ASC LIMIT 1")
    Book oneBook();

    @Select("SELECT * FROM book limit ${start}, ${num}")
    List<Book> findAllBooksLimit(Long start, Long num);

    @Select("select * from book where title like #{keyword} or author like #{keyword} or press like #{keyword} limit 0, 100")
    List<Book> findBookLikeKeywords(@Param("keyword")String keyword);
}