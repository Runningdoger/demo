package competition_2020.controller.test;

import competition_2020.pojo.DisPointExample;
import competition_2020.service.DIsPointService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Nick
 * @Date 2020/2/18--23:19
 * @Description
 */
@Controller
public class testController {

    @Resource
    DIsPointService disPointService;

    @RequestMapping(value = "/test/select" ,method = RequestMethod.GET)
    @ResponseBody
    public List deleteDisPoint() throws Exception{
        List list = disPointService.selectDisPointAndDotName();
        return list;
    }

    @RequestMapping(value = "/test/select2" ,method = RequestMethod.GET)
    @ResponseBody
    public List deleteDisPoint2(@RequestParam(value = "id", required = false) Integer id,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "cname", required = false) String cname) throws Exception{
        List list = disPointService.selectDisPointAndDotName2(id,name,cname);
        return list;
    }

}
