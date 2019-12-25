package top.aiteky.onebook.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.aiteky.onebook.entity.Tag;

import java.util.List;

public interface TagMapper {

    @Select("select count(*) from tag where hasDone = 0")
    Long count();

    @Select("select * from tag where hasDone = 0 limit 0, 1")
    List<Tag> selectByNotHasDone();

    @Update("update tag set hasDone = 1 where id = ${id}")
    boolean setTagHasDone(@Param("id") int id);

    @Select("select id from tag where `name` = #{name}")
    Integer findIdByName(@Param("name")String name);
}