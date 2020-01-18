package competition_2020.pojo;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Nick
 * @Date 2020/1/17--16:03
 * @Description
 */
public class Test117 {

    private CarType[] carTypes;
    private List<Delivery> deliveries;
    private double maxDis;

    public CarType[] getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(CarType[] carTypes) {
        this.carTypes = carTypes;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public double getMaxDis() {
        return maxDis;
    }

    public void setMaxDis(double maxDis) {
        this.maxDis = maxDis;
    }

    public Test117() {
    }

    @Override
    public String toString() {
        return "Test117{" +
                "carTypes=" + Arrays.toString(carTypes) +
                ", deliveries=" + deliveries +
                ", maxDis=" + maxDis +
                '}';
    }
}
