package top.aiteky.onebook.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import top.aiteky.onebook.entity.BookTag;
import top.aiteky.onebook.entity.BookUrl;

public interface BookTagMapper {

    @Insert("insert into book_tag(book_id, tag_id) values(${bid}, ${tid})")
    int insert(@Param("bid")Long bid, @Param("tid")int tid);

}