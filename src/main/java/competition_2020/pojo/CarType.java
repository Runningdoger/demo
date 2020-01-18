package competition_2020.pojo;

public class CarType {
    private double speed;
    private double capacity;
    private int count;
    public CarType(double speed, double capacity, int count) {
        this.speed = speed;
        this.capacity = capacity;
        this.count = count;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "capacity=" + capacity +
                '}';
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
