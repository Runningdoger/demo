package competition_2020.controller;

import competition_2020.mapper.DotMapper;
import competition_2020.pojo.*;
import competition_2020.service.CarService;
import competition_2020.service.DIsPointService;
import competition_2020.service.DeliveryService;
import competition_2020.service.DotService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Nick
 * @Date 2020/2/14--12:00
 * @Description
 */
@Controller
public class SubController {

    @Resource
    private DotService dotService;

    @Resource
    private DIsPointService disPointService;

    @Resource
    private DeliveryService deliveryService;

    @Resource
    private CarService carService;

    public Map MapReturnTrue (){
        Map<String, String> map = new HashMap<>();
        map.put("key","100");
        return map;
    }

    /**
     * 配送中心更新
     * @param id
     * @param name
     * @param longitude
     * @param latitude
     * @param s_time
     * @param e_time
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateDot" ,method = RequestMethod.POST)
    @ResponseBody
    public Map updateDotSelective(@RequestParam(value = "id", required = true) Integer id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "longitude", required = false) String longitude,
                                  @RequestParam(value = "latitude", required = false) String latitude,
                                  @RequestParam(value = "s_time", required = false) String s_time,
                                  @RequestParam(value = "e_time", required = false) String e_time) throws Exception{

        Dot dot = new Dot();
        if (name != null) {
            dot.setName(name);
        }
        if (longitude != null) {
            dot.setLongitude(longitude);
        }
        if (latitude != null) {
            dot.setLatitude(latitude);
        }
        if (s_time != null) {
            dot.setsTime(s_time);
        }
        if (e_time != null) {
            dot.seteTime(e_time);
        }
        DotExample example = new DotExample();
        example.createCriteria().andIdEqualTo(id);
        dotService.updateSelective(dot,example);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 配送中心添加
     * @param id
     * @param name
     * @param longitude
     * @param latitude
     * @param s_time
     * @param e_time
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addDot" ,method = RequestMethod.POST)
    @ResponseBody
    public Map addDot(@RequestParam(value = "id", required = false) Integer id,
                                  @RequestParam(value = "name", required = true) String name,
                                  @RequestParam(value = "longitude", required = true) String longitude,
                                  @RequestParam(value = "latitude", required = true) String latitude,
                                  @RequestParam(value = "s_time", required = true) String s_time,
                                  @RequestParam(value = "e_time", required = false) String e_time) throws Exception{
        Dot dot = new Dot();
        dot.setId(id);
        dot.setName(name);
        dot.setLongitude(longitude);
        dot.setLatitude(latitude);
        dot.setsTime(s_time);
        dot.seteTime(e_time);
        dotService.insertSelective(dot);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 通过id删除配送中心
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteDot" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteDot(@RequestParam(value = "id", required = true) Integer id) throws Exception{
        DotExample example = new DotExample();
        example.createCriteria().andIdEqualTo(id);
        dotService.deleteByExample(example);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 批量删除配送点
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAllDOt" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteAllDOt(@RequestParam(value = "ids", required = false) String ids) throws Exception{
        String[] strs = ids.split(",");
        List<Integer> idss = new ArrayList<>();

        for(String str:strs){
            idss.add(Integer.parseInt(str));
        }
        dotService.deleteAll(idss);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 通过id或者配送中心名称查询配送中心信息
     * @param id
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectDot" ,method = RequestMethod.GET)
    @ResponseBody
    public List addDot(@RequestParam(value = "id", required = false) Integer id,
                       @RequestParam(value = "name", required = false) String name) throws Exception{
        List<Dot> list = new ArrayList<>();
        DotExample example = new DotExample();
        if (id != null) {
            example.createCriteria().andIdEqualTo(id);
            list = dotService.selectByExample(example);
        }
        if (name != null) {
            example.createCriteria().andNameEqualTo(name);
            list = dotService.selectByExample(example);
        }
        return list;
    }

    /**
     * 返回所有配送中心信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/returnDot" ,method = RequestMethod.GET)
    @ResponseBody
    public List returnDot() throws Exception{

        List<Dot> list = dotService.selectByExample(null);

        return list;

    }

    /**
     * 查询所有配送中心名称
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectDotName" ,method = RequestMethod.GET)
    @ResponseBody
    public List selectDotName() throws Exception{
        return dotService.selectName();
    }





    /**
     * 通过id删除配送点
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteDisPointById" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteDisPoint(@RequestParam(value = "id", required = true) Integer id) throws Exception{
        DisPointExample example = new DisPointExample();
        example.createCriteria().andIdEqualTo(id);
        disPointService.deleteByExample(example);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 查询所有配送点及所属的配送中心
     * @return
     */
    @RequestMapping(value = "/selectDisPointAll" ,method = RequestMethod.GET)
    @ResponseBody
    public List selectDisPointAndDotNameAll(){
        return disPointService.selectDisPointAndDotName();
    }

    /**
     * 根据配送点id或者配送点名称或者配送中心名称查询配送点和对应的配送中心
     * @param id
     * @param name
     * @param cname
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectDisPoint" ,method = RequestMethod.GET)
    @ResponseBody
    public List selectDisPointAndDotName(@RequestParam(value = "id", required = false) Integer id,
                                @RequestParam(value = "dis_name", required = false) String name,
                                @RequestParam(value = "dot_name", required = false) String cname) throws Exception{
        List list = disPointService.selectDisPointAndDotName2(id,name,cname);
        return list;
    }

    /**
     * 增加配送点信息
     * @param id
     * @param name
     * @param cname
     * @param longitude
     * @param latitude
     * @param s_time
     * @param e_time
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertDisPoint" ,method = RequestMethod.POST)
    @ResponseBody
    public Map insertDisPoint(@RequestParam(value = "id", required = false) Integer id,
                                @RequestParam(value = "dis_name", required = true) String name,
                                @RequestParam(value = "dot_name", required = true) String cname,
                                @RequestParam(value = "longitude", required = true) String longitude,
                                @RequestParam(value = "latitude", required = true) String latitude,
                                @RequestParam(value = "s_time", required = true) String s_time,
                                @RequestParam(value = "s_time", required = false) String e_time) throws Exception{
        Dot dot = dotService.selectIdByName(cname);
        int dot_id = dot.getId();
        DisPoint disPoint = new DisPoint();
        disPoint.setId(id);
        disPoint.setDotId(dot_id);
        disPoint.setName(name);
        disPoint.setLongitude(longitude);
        disPoint.setLatitude(latitude);
        disPoint.setsTime(s_time);
        disPoint.seteTime(e_time);
        disPointService.insertSelective(disPoint);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 修改配送点信息
     * @param id
     * @param name
     * @param cname
     * @param longitude
     * @param latitude
     * @param s_time
     * @param e_time
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateDisPoint" ,method = RequestMethod.POST)
    @ResponseBody
    public Map updateDisPoint(@RequestParam(value = "id", required = false) Integer id,
                               @RequestParam(value = "dis_name", required = false) String name,
                               @RequestParam(value = "dot_name", required = false) String cname,
                               @RequestParam(value = "longitude", required = false) String longitude,
                               @RequestParam(value = "latitude", required = false) String latitude,
                               @RequestParam(value = "s_time", required = false) String s_time,
                               @RequestParam(value = "s_time", required = false) String e_time) throws Exception{
        DisPoint disPoint = new DisPoint();
        if (name != null) {
            disPoint.setName(name);
        }
        if (longitude != null) {
            disPoint.setLongitude(longitude);
        }
        if (latitude != null) {
            disPoint.setLatitude(latitude);
        }
        if (s_time != null) {
            disPoint.setsTime(s_time);
        }
        if (e_time != null) {
            disPoint.seteTime(e_time);
        }
        if (cname != null) {
            Dot dot = dotService.selectIdByName(cname);
            int dot_id = dot.getId();
            disPoint.setDotId(dot_id);
        }
        DisPointExample example = new DisPointExample();
        example.createCriteria().andIdEqualTo(id);
        disPointService.updateSelective(disPoint,example);
        Map map = MapReturnTrue();
        return map;
    }


    /**
     * 批量删除配送点
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAllDisPoint" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteAllDisPoint(@RequestParam(value = "ids", required = false) String ids) throws Exception{
        String[] strs = ids.split(",");
        List<Integer> idss = new ArrayList<>();

        for(String str:strs){
            idss.add(Integer.parseInt(str));
        }
        disPointService.deleteAll(idss);
        Map map = MapReturnTrue();
        return map;
    }




    /**
     * 通过id删除配送员
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteDeliveryById" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteDeliveryById(@RequestParam(value = "id", required = true) Integer id) throws Exception{
        DeliveryExample example = new DeliveryExample();
        example.createCriteria().andIdEqualTo(id);
        deliveryService.deleteByExample(example);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 查询所有配送员及所属的配送中心
     * @return
     */
    @RequestMapping(value = "/selectDeliveryAll" ,method = RequestMethod.GET)
    @ResponseBody
    public List selectDeliveryAndDotNameAll(){
        return deliveryService.selectDeliveryAndDotName();
    }

    /**
     * 根据配送员id或者配送员姓名或者配送中心名称查询配送员和对应的配送中心
     * @param id
     * @param name
     * @param cname
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectDelivery" ,method = RequestMethod.GET)
    @ResponseBody
    public List selectDeliveryAndDotName(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "dis_name", required = false) String name,
                                         @RequestParam(value = "dot_name", required = false) String cname) throws Exception{
        List list = deliveryService.selectDeliveryAndDotName2(id,name,cname);
        return list;
    }

    /**
     * 增加配送员信息
     * @param id
     * @param name
     * @param cname
     * @param phone
     * @param sex
     * @param head_url
     * @param age
     * @param power
     * @param s_time
     * @param e_time
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertDelivery" ,method = RequestMethod.POST)
    @ResponseBody
    public Map insertDelivery(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam(value = "de_name", required = true) String name,
                              @RequestParam(value = "dot_name", required = true) String cname,
                              @RequestParam(value = "phone", required = true) String phone,
                              @RequestParam(value = "sex", required = true) String sex,
                              @RequestParam(value = "head_url", required = true) String head_url,
                              @RequestParam(value = "age", required = true) String age,
                              @RequestParam(value = "power", required = true) Integer power,
                              @RequestParam(value = "s_time", required = true) String s_time,
                              @RequestParam(value = "s_time", required = false) String e_time) throws Exception {
        Dot dot = dotService.selectIdByName(cname);
        int dot_id = dot.getId();
        Delivery delivery = new Delivery();
        delivery.setName(name);
        delivery.setPhone(phone);
        delivery.setSex(sex);
        delivery.setHeadUrl(head_url);
        delivery.setAge(age);
        delivery.setPower(power);
        delivery.setsTime(s_time);
        delivery.seteTime(e_time);
        delivery.setDotId(dot_id);
        deliveryService.insertSelecttive(delivery);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 修改配送员信息
     * @param id
     * @param name
     * @param cname
     * @param phone
     * @param sex
     * @param head_url
     * @param age
     * @param power
     * @param s_time
     * @param e_time
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateDelivery" ,method = RequestMethod.POST)
    @ResponseBody
    public Map updateDelivery(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam(value = "de_name", required = false) String name,
                              @RequestParam(value = "dot_name", required = false) String cname,
                              @RequestParam(value = "phone", required = false) String phone,
                              @RequestParam(value = "sex", required = false) String sex,
                              @RequestParam(value = "head_url", required = false) String head_url,
                              @RequestParam(value = "age", required = false) String age,
                              @RequestParam(value = "power", required = false) Integer power,
                              @RequestParam(value = "s_time", required = false) String s_time,
                              @RequestParam(value = "s_time", required = false) String e_time) throws Exception{
        Delivery delivery = new Delivery();
        if (name != null) {
            delivery.setName(name);
        }
        if (phone != null) {
            delivery.setPhone(phone);
        }
        if (sex != null) {
            delivery.setSex(sex);
        }
        if (head_url != null) {
            delivery.setHeadUrl(head_url);
        }
        if (age != null) {
            delivery.setAge(age);
        }
        if (power != null) {
            delivery.setPower(power);
        }
        if (s_time != null) {
            delivery.setsTime(s_time);
        }
        if (e_time != null) {
            delivery.seteTime(e_time);
        }
        if (cname != null) {
            Dot dot = dotService.selectIdByName(cname);
            int dot_id = dot.getId();
            delivery.setDotId(dot_id);
        }
        DeliveryExample example = new DeliveryExample();
        example.createCriteria().andIdEqualTo(id);
        deliveryService.updateSelective(delivery,example);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 批量删除配送员
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAllDelivery" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteAllDelivery(@RequestParam(value = "ids", required = false) String ids) throws Exception{
        String[] strs = ids.split(",");
        List<Integer> idss = new ArrayList<>();

        for(String str:strs){
            idss.add(Integer.parseInt(str));
        }
        deliveryService.deleteAll(idss);
        Map map = MapReturnTrue();
        return map;
    }




    /**
     * 通过id删除车辆
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteCarById" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteCarById(@RequestParam(value = "id", required = true) Integer id) throws Exception{
        carExample example = new carExample();
        example.createCriteria().andIdEqualTo(id);
        carService.deleteByExample(example);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 查询所有车辆信息
     * @return
     */
    @RequestMapping(value = "/selectCarAll" ,method = RequestMethod.GET)
    @ResponseBody
    public List selectCarAll(){
        return carService.selectCarAndDotAndDelivery();
    }

    /**
     * 根据车辆id或者车辆车牌号或者车辆驾驶员姓名或者配送中心名称查询车辆信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectCar" ,method = RequestMethod.GET)
    @ResponseBody
    public List selectCar(@RequestParam(value = "id", required = false) Integer id,
                          @RequestParam(value = "number", required = false) String number,
                                         @RequestParam(value = "de_name", required = false) String came,
                                         @RequestParam(value = "dot_name", required = false) String dname) throws Exception{
        List list = carService.selectCarAndDotAndDelivery2(id,number,came,dname);
        return list;
    }

    /**
     * 增加车辆信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertCar" ,method = RequestMethod.POST)
    @ResponseBody
    public Map insertCar(@RequestParam(value = "id", required = false) Integer id,
                              @RequestParam(value = "number", required = true) String number,
                              @RequestParam(value = "weight", required = true) Integer weight,
                              @RequestParam(value = "mileage", required = true) Integer mileage,
                              @RequestParam(value = "de_name", required = true) String cname,
                              @RequestParam(value = "dot_name", required = true) String dname,
                              @RequestParam(value = "s_time", required = true) String s_time,
                              @RequestParam(value = "s_time", required = false) String e_time) throws Exception {
        Dot dot = dotService.selectIdByName(dname);
        int dot_id = dot.getId();
        Delivery delivery = deliveryService.selectByName(cname);
        int de_id = delivery.getId();
        car car = new car();
        car.setId(id);
        car.setNumber(number);
        car.setWeight(weight);
        car.setMileage(mileage);
        car.setDeliveryId(de_id);
        car.setDotId(dot_id);
        car.setsTime(s_time);
        car.seteTime(e_time);
        carService.insertSelective(car);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 修改车辆信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateCar" ,method = RequestMethod.POST)
    @ResponseBody
    public Map updateCar(@RequestParam(value = "id", required = false) Integer id,
                         @RequestParam(value = "number", required = false) String number,
                         @RequestParam(value = "weight", required = false) Integer weight,
                         @RequestParam(value = "mileage", required = false) Integer mileage,
                         @RequestParam(value = "de_name", required = false) String cname,
                         @RequestParam(value = "dot_name", required = false) String dname,
                         @RequestParam(value = "s_time", required = false) String s_time,
                         @RequestParam(value = "s_time", required = false) String e_time) throws Exception{
        car cars = new car();
        if (number != null) {
            cars.setNumber(number);
        }
        if (weight != null) {
            cars.setWeight(weight);
        }
        if (mileage != null) {
            cars.setMileage(mileage);
        }
        if (cname != null) {
            Delivery delivery = new Delivery();
            int de_id = delivery.getId();
            cars.setDeliveryId(de_id);
        }
        if (s_time != null) {
            cars.setsTime(s_time);
        }
        if (e_time != null) {
            cars.seteTime(e_time);
        }
        if (dname != null) {
            Dot dot = dotService.selectIdByName(cname);
            int dot_id = dot.getId();
            cars.setDotId(dot_id);
        }
        carExample example = new carExample();
        example.createCriteria().andIdEqualTo(id);
        carService.updateSelective(cars,example);
        Map map = MapReturnTrue();
        return map;
    }

    /**
     * 批量删除车辆信息
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAllCar" ,method = RequestMethod.POST)
    @ResponseBody
    public Map deleteAllCar(@RequestParam(value = "ids", required = false) String ids) throws Exception{
        String[] strs = ids.split(",");
        List<Integer> idss = new ArrayList<>();

        for(String str:strs){
            idss.add(Integer.parseInt(str));
        }
        carService.deleteAll(idss);
        Map map = MapReturnTrue();
        return map;
    }

}
