package competition_2020.pojo;

import java.util.Arrays;

public class Delivery {
    String longitude;
    String latitude;
    double demand;
    double[] dis;

    public Delivery(String longitude, String latitude, double demand, double[] dis) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.demand = demand;
        this.dis = dis;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public double getDemand() {
        return demand;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }

    public double[] getDis() {
        return dis;
    }

    public void setDis(double[] dis) {
        this.dis = dis;
    }

    @Override
    public String toString() {
        return "delivery{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", demand=" + demand +
                ", dis=" + Arrays.toString(dis) +
                '}';
    }
}
