package competition_2020.service;

import competition_2020.mapper.DeliveryMapper;
import competition_2020.pojo.Delivery;
import competition_2020.pojo.DeliveryExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/23--18:30
 * @Description
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Resource
    DeliveryMapper deliveryMapper;

    @Override
    public List selectDeliveryAndDotName() {
        return deliveryMapper.selectDeliveryAndDot();
    }

    @Override
    public void insertSelecttive(Delivery record) {
        deliveryMapper.insertSelective(record);
    }

    @Override
    public void deleteByExample(DeliveryExample record) {
        deliveryMapper.deleteByExample(record);
    }

    @Override
    public Delivery selectByName(String name) {
        return deliveryMapper.selectByName(name);
    }

    @Override
    public void deleteAll(List ids) {
        deliveryMapper.deleteAll(ids);
    }

    @Override
    public void updateSelective(Delivery record, DeliveryExample example) {
        deliveryMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List selectDeliveryAndDotName2(Integer id, String name, String cname) {
        return selectDeliveryAndDotName2(id,name,cname);
    }
}
