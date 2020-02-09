package competition_2020.service;

import competition_2020.config.MyUserDetails;
import competition_2020.mapper.UserMapper;
import competition_2020.pojo.User;
import competition_2020.pojo.UserExample;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserExample userExample= new UserExample();
        userExample.or().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==0||users.isEmpty()==true) {
            throw new UsernameNotFoundException("not exist");
        }
        return new MyUserDetails(users.get(0));
    }



}
