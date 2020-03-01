package competition_2020.service;

import competition_2020.pojo.DisPoint;
import competition_2020.pojo.DisPointExample;
import competition_2020.pojo.Dot;
import competition_2020.pojo.DotExample;

import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/18--17:23
 * @Description
 */
public interface DIsPointService {

    //添加
    void insertSelective(DisPoint record);

    //删除
    void deleteByExample(DisPointExample example);

    //批量删除
    void deleteAll(List ids);

    //更新
    void updateSelective(DisPoint record,DisPointExample example);

    //查询
    List<DisPoint> selectByExample(DisPointExample example);

    //查询所有配送点和对应的配送中心
    List selectDisPointAndDotName();

    //根据配送点id或者配送点名称或者配送中心名称查询配送点和对应的配送中心
    List selectDisPointAndDotName2(Integer id,String name,String cname);

}
