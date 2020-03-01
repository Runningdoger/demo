package competition_2020.service;

import competition_2020.pojo.Delivery;
import competition_2020.pojo.DeliveryExample;

import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/23--10:45
 * @Description
 */
public interface DeliveryService {

    //查询所有配送员和对应的配送中心
    List selectDeliveryAndDotName();

    //添加
    void insertSelecttive(Delivery record);

    //删除
    void deleteByExample(DeliveryExample record);

    //通过配送员姓名查询信息
    Delivery selectByName(String name);

    //批量删除
    void deleteAll(List ids);

    //更新
    void updateSelective(Delivery record, DeliveryExample example);

    //根据配送员id或者配送员名称或者配送中心名称查询配送员和对应的配送中心
    List selectDeliveryAndDotName2(Integer id,String name,String cname);

}
