package competition_2020.mapper;

import competition_2020.pojo.Dot;
import competition_2020.pojo.DotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DotMapper {
    long countByExample(DotExample example);

    int deleteByExample(DotExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dot record);

    int insertSelective(Dot record);

    List<Dot> selectByExample(DotExample example);

    Dot selectByPrimaryKey(Integer id);

    Dot selectByName(String name);

    List selectName();

    int deleteAll(List ids);

    int updateByExampleSelective(@Param("record") Dot record, @Param("example") DotExample example);

    int updateByExample(@Param("record") Dot record, @Param("example") DotExample example);

    int updateByPrimaryKeySelective(Dot record);

    int updateByPrimaryKey(Dot record);
}