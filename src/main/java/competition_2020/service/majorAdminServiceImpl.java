package competition_2020.service;

import competition_2020.mapper.majorAdminMapper;
import competition_2020.pojo.majorAdmin;
import competition_2020.pojo.majorAdminExample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Nick
 * @Date 2020/1/13--15:07
 * @Description
 */
@Service
@Transactional
public class majorAdminServiceImpl implements majorAdminService {

    @Resource
    majorAdminMapper majorAdminMapper;

    //总管理员登录
    @Override
    public boolean login(String name, String password) {
        boolean a = false;
        majorAdmin major = new majorAdmin();
        majorAdminExample example = new majorAdminExample();
        example.createCriteria().andNameEqualTo(name);
        List<majorAdmin> majorAdmin = majorAdminMapper.selectByExample(example);
        major = majorAdmin.get(0);
        if (major != null) {
            if (major.getPassword().equals(password)) {
                a = true;
            } else {
                a = false;
            }
        } else {
            a = false;
        }
        return a;
    }
}