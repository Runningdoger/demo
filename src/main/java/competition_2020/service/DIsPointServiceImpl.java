package competition_2020.service;

import competition_2020.mapper.DisPointMapper;
import competition_2020.pojo.DisPoint;
import competition_2020.pojo.DisPointExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/18--17:43
 * @Description
 */
@Service
public class DIsPointServiceImpl implements DIsPointService {

    @Resource
    DisPointMapper disPointMapper;

    @Override
    public void insertSelective(DisPoint record) {
        disPointMapper.insertSelective(record);
    }

    @Override
    public void deleteByExample(DisPointExample example) {
        disPointMapper.deleteByExample(example);
    }

    @Override
    public void deleteAll(List ids) {
        disPointMapper.deleteAll(ids);
    }

    @Override
    public void updateSelective(DisPoint record, DisPointExample example) {
        disPointMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<DisPoint> selectByExample(DisPointExample example) {
        List<DisPoint> list = disPointMapper.selectByExample(example);
        return null;
    }

    @Override
    public List selectDisPointAndDotName() {
        return disPointMapper.selectDisPointAndDot();
    }

    @Override
    public List selectDisPointAndDotName2(Integer id, String name,String cname) {
        return disPointMapper.selectDisPointAndDot2(id,name,cname);
    }

}
