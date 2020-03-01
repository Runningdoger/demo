package competition_2020.algorithm;

import competition_2020.pojo.CarType;

import java.math.BigDecimal;

public class Genatic {
    private CarType[] carTypes;
    private int cityCount;
    private double[][] dis;
    private double[] demand;
    private double maxDis;
    private double[] arrTime;

    public Genatic(CarType[] carTypes, int cityCount, double[][] dis, double[] demand, double maxDis, double[] arrTime) {
        this.carTypes = carTypes;
        this.cityCount = cityCount;
        this.dis = dis;
        this.demand = demand;
        this.maxDis = maxDis;
        this.arrTime = arrTime;
    }

    public void start() {
        dis=floyd(dis);
        double fitness=0,sum=0,rate=0;
        Population.Chrom chrom=null,bestChrom=null;
        double[] f=new double[100];
        for(int j=0;j<100;j++) {
            chrom=null;
            System.out.println(j+1);//0.85 0.75 0.08 0.15   0.95, 0.16  0.85, 0.25
            Population population = new Population(cityCount, 100, 0.95, 0.08, demand, dis, maxDis, carTypes, arrTime,1, 200, 10);
            Population population1 = new Population(cityCount, 100, 0.85, 0.15, demand, dis, maxDis, carTypes, arrTime,1, 200, 10);
            long tt = System.currentTimeMillis();
            population.init();
            population1.init();
            long tt1 = System.currentTimeMillis();
            long tt2=0,tt3=0,tt4=0,tt5=0;
            int i=0,c=0;
            double last=0;
            while(i>=0){
                i++;
                long t1 = System.currentTimeMillis();
                population.access();
                population1.access();
                long t2 = System.currentTimeMillis();
                tt2+=t2-t1;
                population.intelsect();
                population1.intelsect();
                long t3 = System.currentTimeMillis();
                tt3+=t3-t2;
                population.mutate();
                population1.mutate();
                long t4 = System.currentTimeMillis();
                tt4+=t4-t3;
                if(i%1000==999){
                    population.swap(population1);
                    long t5=System.currentTimeMillis();
                    tt5+=t5-t4;
                }
                double cur=population.getBestChrom().getFitness();
                if(cur<population1.getBestChrom().getFitness()){
                    cur=population1.getBestChrom().getFitness();
                }
                Population.Chrom chr=population1.getBestChrom();
                if(chr.getFitness()<population.getBestChrom().getFitness()){
                    chr=population.getBestChrom();
                }
                if(cur>last){
                    last=cur;
                    c=0;
                }else{
                    c++;
                }
                if(c==5000) {
                    break;
                }
            }
            System.out.println("初始化用时："+(tt1-tt)+"ms");
            System.out.println("评估用时："+tt2+"ms");
            System.out.println("交叉用时："+tt3+"ms");
            System.out.println("变异用时："+tt4+"ms");
            System.out.println("交换用时："+tt5+"ms");
            System.out.println("总用时: "+(tt1-tt+tt2+tt3+tt4+tt5)+"ms");
            population.access();
            population1.access();


            if(chrom==null){
                chrom=population.getBestChrom();
            }
            else{
                if(population1.getBestChrom().getFitness()>chrom.getFitness()){
                    chrom = population1.getBestChrom();
                }
            }
            System.out.println(chrom);
            if(bestChrom==null){
                bestChrom=chrom;
            }
            else{
                if(bestChrom.getFitness()<chrom.getFitness()){
                    bestChrom=chrom;
                }
            }
            sum+=chrom.getDis();
            f[j]=chrom.getFitness();
            fitness+=f[j];
            rate+=chrom.getAveLoadRate();
        }


        System.out.println("平均适应度："+fitness/100+" 平均路程："+sum/100+" 平均满载率："+rate/100);
        double s=0;
        for(int i=0;i<100;i++){
            s+=(f[i]-fitness/100)*(f[i]-fitness/100);
        }
        s=s/100;
        System.out.println("标准差："+ Math.sqrt(s));
        System.out.println("找到的最优解："+bestChrom);
    }


    /**
     * 求多源最短路
     */
    public double[][] floyd(double[][] dis) {
//        for (int i = 0; i <= cityCount; i++) {
//            for (int j = 0; j <= cityCount; j++) {
//                if (i == j) {
//                    dis[i][j] = 0;
//                } else {
//                    dis[i][j] = 0x3f3f3f3f;
//                }
//            }
//        }
        for (int k = 0; k < cityCount; k++) {
            for (int i = 0; i < cityCount; i++) {
                for (int j = 0; j < cityCount; j++) {
                    dis[i][j] = min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }

        for (int i = -2; i < cityCount; i++) {
            if (i < 0) {
                System.out.printf("%15c", ' ');
            } else {
                System.out.printf("%15c", 'A' + i - 1);
            }
        }
        System.out.println();
        for (int i = -2; i < cityCount; i++) {
            if (i < 0) {
                System.out.printf("%15c", ' ');
            } else {
                System.out.printf("%15d", i);
            }
        }
        System.out.println();

        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%15c", 'A' + i - 1);
            System.out.printf("%15d", i);

            for (int j = 0; j < cityCount; j++) {
                BigDecimal bg = new BigDecimal(dis[i][j]);
                double f1 = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                System.out.printf("%15f", f1);
            }
            System.out.println();
        }
        return dis;
    }

    /**
     * 求最小值
     *
     * @param a
     * @param b
     * @return
     */
    private double min(double a, double b) {
        return a > b ? b : a;
    }

}




