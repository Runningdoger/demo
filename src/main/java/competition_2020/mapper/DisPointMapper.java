package competition_2020.mapper;

import competition_2020.pojo.DisPoint;
import competition_2020.pojo.DisPointExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DisPointMapper {
    long countByExample(DisPointExample example);

    int deleteByExample(DisPointExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DisPoint record);

    int insertSelective(DisPoint record);

    List<DisPoint> selectByExample(DisPointExample example);

    DisPoint selectByPrimaryKey(Integer id);

    List selectDisPointAndDot();

    int deleteAll(List ids);

    List selectDisPointAndDot2(Integer id,String name,String cname);

    int updateByExampleSelective(@Param("record") DisPoint record, @Param("example") DisPointExample example);

    int updateByExample(@Param("record") DisPoint record, @Param("example") DisPointExample example);

    int updateByPrimaryKeySelective(DisPoint record);

    int updateByPrimaryKey(DisPoint record);
}