package competition_2020.mapper;

import competition_2020.pojo.sAdmin;
import competition_2020.pojo.sAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface sAdminMapper {
    long countByExample(sAdminExample example);

    int deleteByExample(sAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(sAdmin record);

    int insertSelective(sAdmin record);

    List<sAdmin> selectByExample(sAdminExample example);

    sAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") sAdmin record, @Param("example") sAdminExample example);

    int updateByExample(@Param("record") sAdmin record, @Param("example") sAdminExample example);

    int updateByPrimaryKeySelective(sAdmin record);

    int updateByPrimaryKey(sAdmin record);
}