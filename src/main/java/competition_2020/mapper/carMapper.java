package competition_2020.mapper;

import competition_2020.pojo.car;
import competition_2020.pojo.carExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface carMapper {
    long countByExample(carExample example);

    int deleteByExample(carExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(car record);

    int insertSelective(car record);

    int deleteAll(List ids);

    List selectCarAndDotAndDelivery();

    List selectCarAndDotAndDelivery2(Integer id,String number,String cname,String dname);

    List<car> selectByExample(carExample example);

    car selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") car record, @Param("example") carExample example);

    int updateByExample(@Param("record") car record, @Param("example") carExample example);

    int updateByPrimaryKeySelective(car record);

    int updateByPrimaryKey(car record);
}