package top.aiteky.onebook.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.aiteky.onebook.entity.BookUrl;

import java.util.List;

public interface BookUrlMapper {


    @Insert("insert into book_url(link, tag, hasDone) values(#{link}, #{tag}, ${hasdone})")
    int insert(BookUrl record);

    @Select("select count(link) from book_url where id = #{link}")
    boolean exist(@Param("link") String link);

    @Select("select * from book_url where hasDone = 0 limit 0, 20")
    List<BookUrl> selectByNotHasDone();

    @Update("update book_url set hasDone = 1 where id = ${id}")
    boolean setBookUrlHasDone(@Param("id") Long id);
}