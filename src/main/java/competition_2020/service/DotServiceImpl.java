package competition_2020.service;

import competition_2020.mapper.DotMapper;
import competition_2020.pojo.Dot;
import competition_2020.pojo.DotExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Nick
 * @Date 2020/2/18--11:21
 * @Description
 */
@Service
public class DotServiceImpl implements DotService {

    @Resource
    DotMapper dotMapper;

    @Override
    public void insertSelective(Dot record) {
        dotMapper.insertSelective(record);
    }

    @Override
    public void deleteByExample(DotExample example) {
        dotMapper.deleteByExample(example);
    }

    @Override
    public void deleteAll(List ids) {
        dotMapper.deleteAll(ids);
    }

    @Override
    public void updateSelective(Dot record,DotExample example) {
        dotMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<Dot> selectByExample(DotExample example) {
        List<Dot> list = dotMapper.selectByExample(example);
        return list;
    }

    @Override
    public Dot selectIdByName(String name) {
        return dotMapper.selectByName(name);
    }

    @Override
    public List selectName() {
        return dotMapper.selectName();
    }
}
