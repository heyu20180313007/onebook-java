package top.aiteky.onebook.mapper;

import org.apache.ibatis.annotations.Insert;
import top.aiteky.onebook.entity.SysErr;

public interface SysErrMapper {

    @Insert("INSERT INTO sys_err(`message`, `datetime`, `clazz`) VALUES (#{message}, #{datetime}, #{clazz})")
    int insert(SysErr record);

}