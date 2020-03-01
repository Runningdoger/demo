package competition_2020.service;

import competition_2020.pojo.car;
import competition_2020.pojo.carExample;

import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/24--11:55
 * @Description
 */
public interface CarService {

    //查询所有车辆和对应的配送中心及驾驶员
    List selectCarAndDotAndDelivery();

    //添加
    void insertSelective(car record);

    //删除
    void deleteByExample(carExample record);

    //批量删除
    void deleteAll(List ids);

    //更新
    void updateSelective(car record,carExample example);

    //根据车辆id或者车辆车牌号或者车辆驾驶员姓名或者配送中心名称查询车辆信息
    List selectCarAndDotAndDelivery2(Integer id,String number,String cname,String dname);

}
