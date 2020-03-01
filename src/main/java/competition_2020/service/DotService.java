package competition_2020.service;

import competition_2020.pojo.Dot;
import competition_2020.pojo.DotExample;

import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/18--10:48
 * @Description
 */
public interface DotService {

    //添加
    void insertSelective(Dot record);

    //删除
    void deleteByExample(DotExample example);

    //批量删除
    void deleteAll(List ids);

    //更新
    void updateSelective(Dot record,DotExample example);

    //查询
    List<Dot> selectByExample(DotExample example);

    //根据配送中心名字查询配送中心id
    Dot selectIdByName(String name);

    //查询所有配送中心的名字
    List selectName();

}
