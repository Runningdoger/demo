package competition_2020.service;

import competition_2020.mapper.carMapper;
import competition_2020.pojo.car;
import competition_2020.pojo.carExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/24--12:02
 * @Description
 */
@Service
public class CarServiceImpl implements CarService {

    @Resource
    carMapper carMapper;

    @Override
    public List selectCarAndDotAndDelivery() {
        return carMapper.selectCarAndDotAndDelivery();
    }

    @Override
    public void insertSelective(car record) {
        carMapper.insertSelective(record);
    }

    @Override
    public void deleteByExample(carExample record) {
        carMapper.deleteByExample(record);
    }

    @Override
    public void deleteAll(List ids) {
        carMapper.deleteAll(ids);
    }

    @Override
    public void updateSelective(car record, carExample example) {
        carMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List selectCarAndDotAndDelivery2(Integer id, String number, String cname, String dname) {
        return carMapper.selectCarAndDotAndDelivery2(id,number,cname,dname);
    }
}
