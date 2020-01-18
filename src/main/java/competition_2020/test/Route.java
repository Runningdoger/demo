package competition_2020.test;

import competition_2020.pojo.CarType;
import competition_2020.pojo.Delivery;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private CarType carType;
    private List<Integer> route;
    private List<Delivery> deliveries=new ArrayList<>();
    private double weight;
    private double dis;
    private double overTime;

    public Route(CarType carType, List<Integer> route,double weight,double dis) {
        this.carType = carType;
        this.route = route;
        this.weight=weight;
        this.dis=dis;
    }

    public Route(Route route) {
        this.carType = route.getCarType();
        this.route = new ArrayList<>(route.getRoute().size());
        this.route.addAll(route.getRoute());
        this.weight=route.getWeight();
        this.dis=route.getDis();
        this.overTime=route.getOverTime();
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public double getOverTime() {
        return overTime;
    }

    public void setOverTime(double overTime) {
        this.overTime = overTime;
    }
    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public List<Integer> getRoute() {
        return route;
    }

    public void setRoute(List<Integer> route) {
        this.route = route;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
    }


    @Override
    public String toString() {
        return "Route{" +
                "carType=" + carType +
                ", route=" + route +
                ", 重量：" + weight +
                ", 满载率=" + weight/carType.getCapacity() +
                ", 行驶距离：=" + dis +
                ", 超时：=" + overTime +
                '}'+"\n";
    }
}
