package competition_2020.controller;

import competition_2020.pojo.CarType;
import competition_2020.pojo.Delivery;
import competition_2020.pojo.Test117;
import competition_2020.test.Genatic;
import competition_2020.test.Route;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Nick
 * @Date 2020/1/17--15:49
 * @Description
 *
 * @RequestParam(value = "longitude", required = true) String longitude,
 *                      @RequestParam(value = "latitude", required = true) String latitude,
 *                      @RequestParam(value = "demand", required = true) double demand,
 *                      @RequestParam(value = "dis", required = true) double[] dis
 *
 */
@Controller
public class SAdminController {

    @RequestMapping(value = "/test" , method = RequestMethod.POST)
    @ResponseBody
    public List test(@RequestBody Map<String,List<Delivery>> map) throws Exception{

        List<Delivery> deliveries=map.get("data");
        CarType[] carType = new CarType[1];
        carType[0] = new CarType(10,8,999);
        double maxDis = 35;

        Genatic genatic = new Genatic(carType,deliveries,maxDis);
        genatic.start();
        List<Route> result = genatic.getResult();


        return result;
    }

}
