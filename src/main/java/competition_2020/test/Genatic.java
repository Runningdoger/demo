package competition_2020.test;

import competition_2020.pojo.CarType;
import competition_2020.pojo.Delivery;

import java.util.List;


public class Genatic {
    private List<Delivery> deliveries;
    private CarType[] carTypes;
    private int cityCount;
    private double[][] dis;
    private double[] demand;
    private double maxDis;
    private double[] arrTime;
    private List<Route> result;

    public Genatic(CarType[] carTypes, List<Delivery> deliveries, double maxDis) {
        this.deliveries=deliveries;
        this.carTypes = carTypes;
        this.cityCount = deliveries.size();
        dis=new double[cityCount][cityCount];
        for(int i=0;i<cityCount;i++){
            for(int j=0;j<cityCount;j++){
                dis[i][j]=deliveries.get(i).getDis()[j];
            }
        }
        demand=new double[cityCount];
        for(int i=0;i<cityCount;i++){
            demand[i]=deliveries.get(i).getDemand();
        }
        this.maxDis = maxDis;
        //this.arrTime = arrTime;
    }

    public void start() {
        Population population = new Population(cityCount, 100, 0.85, 0.07, demand, dis, maxDis, carTypes, arrTime,1, 200, 10);
        population.init();

        for (int i = 0; i < 1000; i++) {
            population.access();

            population.intelsect();

            population.mutate();
        }
        population.access();
        List<Route> routes = population.getBestChrom().getRoutes();
        for(int i=0;i<routes.size();i++){
            Route route = routes.get(i);
            for(int j=0;j<route.getRoute().size();j++){

                route.getDeliveries().add(deliveries.get(route.getRoute().get(i)));
            }
        }
        result=routes;
    }

    public List<Route> getResult(){
        return result;
    }

}




