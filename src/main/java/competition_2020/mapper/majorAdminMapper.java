package competition_2020.mapper;

import competition_2020.pojo.majorAdmin;
import competition_2020.pojo.majorAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface majorAdminMapper {
    long countByExample(majorAdminExample example);

    int deleteByExample(majorAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(majorAdmin record);

    int insertSelective(majorAdmin record);

    List<majorAdmin> selectByExample(majorAdminExample example);

    majorAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") majorAdmin record, @Param("example") majorAdminExample example);

    int updateByExample(@Param("record") majorAdmin record, @Param("example") majorAdminExample example);

    int updateByPrimaryKeySelective(majorAdmin record);

    int updateByPrimaryKey(majorAdmin record);
}