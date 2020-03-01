package competition_2020.algorithm;

import competition_2020.pojo.CarType;

import java.util.*;

public class Population {
    /**
     * 前i个个体概率和
     */
    double[] p;
    /**
     * 种群
     */
    private List<Chrom> chroms;
    /**
     * 总适应度
     */
    private double totalFitness;
    /**
     * 种群大小
     */
    private int size;
    /**
     * 最优个体
     */
    private Chrom bestChrom;
    /**
     * 交叉概率
     */
    private double Pc;
    /**
     * 变异概率
     */
    private double Pm;
    /**
     * 个体总距离所占适应值比重
     */
    private double alpha;
    /**
     * 个体平均载重量所占适应值比重
     */
    private double beta;
    /**
     * 个体车辆数所占适应值比重
     */
    private double gamma;
    /**
     * 客户需求
     */
    private double[] demand;
    /**
     * 最晚到达时间
     */
    private double[] arrTime;
    /**
     * 各点间距离
     */
    private double[][] dis;
    /**
     * 最大行驶距离
     */
    private double maxDis;
    /**
     * 车辆类型
     */
    private CarType[] carTypes;
    /**
     * 点数
     */
    private int cityCount;

    private double aveFitness=0;

    //private Queue<Chrom> tabuTable;

    public Population(int cityCount, int size, double pc, double pm, double[] demand, double[][] dis, double maxDis, CarType[] carTypes, double[] arrTime, double alpha, double beta, double gamma) {
        this.size = size;
        Pc = pc;
        Pm = pm;
        this.demand = demand;
        this.dis = dis;
        this.maxDis = maxDis;
        this.carTypes = carTypes;
        p = new double[size];
        this.cityCount = cityCount;
        bestChrom = null;
        this.alpha = alpha;
        this.gamma = gamma;
        this.beta = beta;
        this.arrTime = arrTime;
        //tabuTable=new LinkedList<Chrom>();
    }

    public List<Chrom> getChroms() {
        return chroms;
    }

    /**
     * 初始化种群
     */
    public void init() {
        chroms = new ArrayList<>(200);
        chroms.add(new Chrom(CW.cal(carTypes[0],cityCount,demand,dis,maxDis)));

        while(chroms.size() < size) {
            Chrom chrom = new Chrom();
            //System.out.println(chrom);
            chroms.add(chrom);
        }
    }

    /**
     * 评估函数
     * 根据路线长短、满载辆对一条染色体（解）进行评估
     *      可行解：Fitness=500 / (alpha * 总距离 + beta * (1 - 平均装载率) + gamma * 车辆数）
     *      不可行解：Fitness=1/2000;
     *  并挑选出一个最优个体
     */
    public void access() {
        totalFitness = 0;
        int c=0;
        for (int i = 0; i < size; i++) {
            Chrom chrom = chroms.get(i);
            if (chrom.isTrue()) {
                chrom.assess();
                aveFitness+=chrom.getFitness();
                c++;
                if (bestChrom == null) {
                    bestChrom = chrom;
                } else {
                    if (chrom.getFitness() > bestChrom.getFitness()) {
                        bestChrom = chrom;
                    }
                }
            } else {
                chrom.setFitness(1 / 200);

            }
            if(aveFitness!=0){
                aveFitness/=c;
            }
            totalFitness += chrom.getFitness();
        }

        for (int i = 0; i < chroms.size(); i++) {
            if (i == 0) {
                p[i] = chroms.get(i).getFitness() / totalFitness;
            } else {
                p[i] = p[i - 1] + chroms.get(i).getFitness() / totalFitness;
            }
        }
    }

    /**
     * 轮盘赌选择两个个体
     * @return  返回选中的两个个体
     */
    public Chrom[] select() {
        Chrom[] parent = new Chrom[2];
        int count = 0;
        while (count < 2) {
            double r = Math.random();
            for (int i = 0; i < size; i++) {
                if (p[i] >= r) {
                    parent[count++] = chroms.get(i);
                    break;
                }
            }
        }
        return parent;
    }

    /**
     * 交叉操作
     * 如果随机数大于交叉概率，则直接将父代两条染色体加入下一代
     * 如果选择出的两条染色体为同一条，则重新选择
     * 交叉算子使用顺序交叉
     * 终止条件为达到种群数量-1，剩下一个位置为最优个体预留
     */
    public void intelsect() {
        List<Chrom> newChroms = new ArrayList<>(size);
        while (newChroms.size() < size - 1) {
            Chrom[] parent = select();
            double r = Math.random();
            if (r <= Pc) {
                if (!equal(parent[0], parent[1])) {
                    List<Chrom> childs = orderedCrossOver(parent);
                    newChroms.addAll(childs);
                } else {
                    continue;
                }
            }
        }

        while (newChroms.size() > size - 1) {
            newChroms.remove(newChroms.size() - 1);
        }
        chroms = newChroms;
    }

//    /**
//     * 对两个个体使用顺序交叉
//     * @param parent 父代个体
//     * @return  子代个体
//     */
//    public List<Chrom> orderedCrossOver(Chrom[] parent) {
//        Random rd = new Random();
//        double fit=(parent[0].getFitness()+parent[1].getFitness())/2;
//        int index1 = rd.nextInt(cityCount - 1);
//        int index2 = rd.nextInt(cityCount - 1);
//        while ((index1 == 0 && index2 == cityCount - 1)||(index1==index2)) {
//            index1 = rd.nextInt(cityCount - 1);
//            index2 = rd.nextInt(cityCount - 1);
//        }
//
//        if (index1 > index2) {
//            int t = index1;
//            index1 = index2;
//            index2 = t;
//        }
//
//        List<Chrom> childs = new ArrayList<>(2);
//        List<Integer> childRoute1 = new ArrayList<>(size);
//        List<Integer> childRoute2 = new ArrayList<>(size);
//        List<Integer> parent1 = new ArrayList<>(parent[0].getArray());
//        List<Integer> parent2 = new ArrayList<>(parent[1].getArray());
//        for (int i = index1; i <= index2; i++) {
//            childRoute1.add(parent1.get(i));
//            childRoute2.add(parent2.get(i));
//        }
//        parent1.removeAll(childRoute2);
//        parent2.removeAll(childRoute1);
//        int i = 0, index = 0;
//        while (i < cityCount - 1) {
//            if (i == index1) {
//                i = index2 + 1;
//                if (i >= cityCount - 1) {
//                    break;
//                }
//            }
//            childRoute1.add(i, parent2.get(index));
//            childRoute2.add(i, parent1.get(index));
//            index++;
//            i++;
//        }
//        Chrom child1 = new Chrom(childRoute1);
//        Chrom child2 = new Chrom(childRoute2);
//        child1.assess();
//        child2.assess();
//        if(child1.isTrue()) {
//            if (child1.getFitness() >= fit) {
//                childs.add(child1);
//                flashTabuTable(child1);
//            } else {
//                if (!judge(child1)) {
//                    childs.add(child1);
//                }
//            }
//        }
//        if(child2.isTrue) {
//            if (child2.getFitness() >= fit) {
//                childs.add(child2);
//                flashTabuTable(child2);
//            } else {
//                if (!judge(child2)) {
//                    childs.add(child2);
//                }
//            }
//        }
//        if(childs.size()<=1){
//            if(parent[0].getFitness()>parent[1].getFitness()){
//                childs.add(parent[0]);
//            }else{
//                childs.add(parent[1]);
//            }
//        }
//        return childs;
//    }
    /**
     * 对两个个体使用顺序交叉
     * @param parent 父代两个个体
     * @return  子代两个个体
     */
    public List<Chrom> orderedCrossOver(Chrom[] parent) {

        Random rd = new Random();
        int index1 = rd.nextInt(cityCount - 1);
        int index2 = rd.nextInt(cityCount - 1);
        if (index1 > index2) {
            int t = index1;
            index1 = index2;
            index2 = t;
        }
        if (index1 == 0 && index2 == cityCount - 1) {
            return Arrays.asList(parent);
        }
        List<Chrom> childs = new ArrayList<>(2);
        List<Integer> child1 = new ArrayList<>(size);
        List<Integer> child2 = new ArrayList<>(size);
        List<Integer> parent1 = new ArrayList<>(parent[0].getArray());
        List<Integer> parent2 = new ArrayList<>(parent[1].getArray());
        for (int i = index1; i <= index2; i++) {
            child1.add(parent1.get(i));
            child2.add(parent2.get(i));
        }
        parent1.removeAll(child2);
        parent2.removeAll(child1);
        int i = 0, index = 0;
        while (i < cityCount - 1) {
            if (i == index1) {
                i = index2 + 1;
                if (i >= cityCount - 1) {
                    break;
                }
            }
            child1.add(i, parent2.get(index));
            child2.add(i, parent1.get(index));
            index++;
            i++;
        }
        childs.add(new Chrom(child1));
        childs.add(new Chrom(child2));
        return childs;
    }
//    private boolean judge(Chrom child) {
//        for (Chrom chrom : tabuTable) {
//            if(equal(chrom,child)){
//                return true;
//            }
//        }
//        flashTabuTable(child);
//        return false;
//    }
//
//    private void flashTabuTable(Chrom chrom) {
//        if(tabuTable.size()==100){
//            tabuTable.poll();
//        }
//        tabuTable.offer(chrom);
//    }

    /**
     * 判断两个染色体是否相同
     * @param a
     * @param b
     * @return
     */
    public boolean equal(Chrom a, Chrom b) {
        List<Integer> aList = a.getArray();
        List<Integer> bList = b.getArray();
        for (int i = 0; i < aList.size(); i++) {
            if (!aList.get(i).equals(bList.get(i))) {
                return false;
            }
        }
        return true;
    }

    public Chrom getBestChrom() {
        return bestChrom;
    }

    /**
     * 变异操作
     * 对刚才交叉产生的种群数量-1个个体进行变异
     * 如果随机数大于变异概率，则该个体不进行变异
     * 若小于随机交换其序列两个客户编号
     * 最后将最优个体加入到种群中
     */
    public void mutate() {
        for (int i = 0; i < chroms.size(); i++) {
            Chrom chrom = chroms.get(i);
//            double p=Pm;
//            if(chrom.getFitness()<=aveFitness){
//                p+=0.05;
//            }
            if (Math.random() < Pm) {
                double r=Math.random();
                if(r<0.5){
                    chrom.swapMutate();
                }else if(r>=0.5&&r<0.75){
                    chrom.reverseMutate();
                }else{
                    chrom.shiftMutate();
                }

            }
        }
        chroms.add(bestChrom);
    }

    @Override
    public String toString() {
        return "Population{" +
                "chroms=" + chroms +
                '}';
    }

    public void swap(Population population1) {
        Random rd=new Random();
        int num=rd.nextInt(size/2);
        List<Chrom> s1=new ArrayList<>(num+1);
        List<Chrom> s2=new ArrayList<>(num+1);
        if(!equal(bestChrom,population1.getBestChrom())){

            s1.add(population1.getBestChrom());
            s2.add(bestChrom);
            this.chroms.remove(0);
            population1.getChroms().remove(0);
        }
        for(int i=0;i<num;i++){
            s1.add(this.chroms.get(0));
            chroms.remove(0);
            s2.add(population1.getChroms().get(0));
            population1.getChroms().remove(0);
        }
        this.chroms.addAll(s2);
        population1.getChroms().addAll(s1);
    }

    public class Chrom {

        private List<Route> routes;
        private double fitness;
        private List<Integer> array;
        private boolean isTrue;

        public Chrom(Chrom chrom) {
            array = new ArrayList<>(chrom.getArray().size());
            array.addAll(chrom.getArray());
            List<Route> rs = chrom.getRoutes();
            routes = new ArrayList<>(rs.size());
            for (int i = 0; i < rs.size(); i++) {
                routes.add(new Route(rs.get(i)));
            }
            fitness = chrom.getFitness();
            isTrue = chrom.isTrue();

        }

        public Chrom() {
            routes = new ArrayList<>(20);
            array = new ArrayList<>(cityCount);
            fitness = 0;
            do {
                create();
//            System.out.println(array);
            }
            while (split() == false);
        }

        public Chrom(List<Integer> array) {
            this.array = array;
            routes = new ArrayList<>(20);
            fitness = 0;
            split();
        }

        public boolean isTrue() {
            return isTrue;
        }

        public List<Route> getRoutes() {
            return routes;
        }

        public double getFitness() {
            return fitness;
        }

        public void setFitness(double fitness) {
            this.fitness = fitness;
        }

        public List<Integer> getArray() {
            return array;
        }

        public double getDis() {
            double sum = 0;
            for (int i = 0; i < routes.size(); i++) {
                sum += routes.get(i).getDis();
            }
            return sum;
        }

        public int getOverTime(){
            int overTime=0;
            for(int i=0;i<routes.size();i++){
                overTime+=routes.get(i).getOverTime();
            }
            return overTime;
        }

        public double getAveLoadRate() {
            double sum = 0;
            for (int i = 0; i < routes.size(); i++) {
                sum += routes.get(i).getWeight() / routes.get(i).getCarType().getCapacity();
            }
            return sum / routes.size();
        }

        /**
         * 创造一个包含所有客户编号的随机序列
         */
        public void create() {
            array.clear();
            for (int i = 1; i < cityCount; i++) {
                array.add(i);
            }
            Collections.shuffle(array);
        }

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             /**
         * 对随机产生的客户编号序列进行分割，并判断是否满足约束条件
         * @return
         */
        public boolean split() {
            routes.clear();
            double curDis = 0, curWeight = 0;
            int maxCapacityIndex = carTypes.length - 1, last = 0;
            List<Integer> list = new ArrayList<>(15);
            int[] avaliable = new int[carTypes.length];
            for (int i = 0; i < carTypes.length; i++) {
                avaliable[i] = carTypes[i].getCount();
            }
            for (int i = 0; i < array.size(); i++) {
                int num = array.get(i);
                if (maxCapacityIndex == -1) {
                    return false;
                }
                if (curDis + dis[last][num] + dis[num][0] > maxDis || curWeight + demand[num] > carTypes[maxCapacityIndex].getCapacity()) {
//            if (curWeight + demand[num] > carTypes[maxCapacityIndex].getCapacity()||(dis[last][num]>10&&last!=0)) {
                    if (last == 0) {
                        isTrue = false;
                        return false;
                    } else {
                        int index = findCarType(curWeight);
                        while (avaliable[index] == 0) {
                            if (maxCapacityIndex > index) {
                                index++;
                            } else {
                                index--;
                            }
                        }
                        CarType carType = carTypes[index];
                        avaliable[index]--;
                        while (maxCapacityIndex >= 0 && avaliable[maxCapacityIndex] == 0) {
                            maxCapacityIndex--;
                        }
                        Route route = new Route(carType, list, curWeight, curDis);
                        routes.add(route);
                        last = 0;
                        curDis = 0;
                        curWeight = 0;
                        list = new ArrayList<>(15);
                        i--;
                    }
                } else {
                    curDis += dis[last][num];
                    curWeight += demand[num];
                    last = num;
                    list.add(num);
                    if (i == array.size() - 1) {
                        int index = findCarType(curWeight);
                        CarType carType = carTypes[index];
                        avaliable[index]--;
                        Route route = new Route(carType, list, curWeight, curDis);
                        routes.add(route);
                    }
                }
            }
            isTrue = true;
            //calculateOverTime();
            return true;
        }

        /**
         * 根据载重，选择一辆最合适的车型
         * @param Weight
         * @return
         */
        public int findCarType(double Weight) {
            int l = 0, r = carTypes.length - 1, mid = 0;
            while (l <= r) {
                mid = (l + r) / 2;
                if (Weight < carTypes[mid].getCapacity()) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            return mid;
        }


        @Override
        public String toString() {
            return "Chrom{" +
                    "routes=" + routes +
                    ", fitness=" + fitness +
                    "总路程=" + getDis() +
                    "平均满载=" + getAveLoadRate() +
                    '}' + "\n";
        }

        public void swapMutate(){
                Random rd = new Random();
                int index1 = rd.nextInt(cityCount-1);
                int index2 = rd.nextInt(cityCount-1);
                int val1 = array.get(index1);
                int val2 = array.get(index2);
                array.set(index1, val2);
                array.set(index2, val1);
                split();
        }

        public void calculateOverTime(){
            for(int i=0;i<routes.size();i++){
                double sum=0,t=0;
                int last=0;
                Route route = routes.get(i);
                for(int j=0;j<route.getRoute().size();j++){
                    t+=dis[last][route.getRoute().get(j)]*60/route.getCarType().getSpeed();
                    if(t-arrTime[route.getRoute().get(j)]<0){
                        sum+=arrTime[route.getRoute().get(j)]-t;
                    }
                    t+=5;
                    last=route.getRoute().get(j);
                }
                route.setOverTime(sum);
            }
        }

        public void assess() {
            setFitness(500 / (alpha * getDis() + beta * (1 - getAveLoadRate()) + gamma * getRoutes().size()/**+0.3*chrom.getOverTime()**/));
        }

        public void reverseMutate() {
            Random rd = new Random();
            int index1 = rd.nextInt(cityCount-1);
            int index2 = rd.nextInt(cityCount-1);
            if(index1>index2){
                int t=index1;
                index1=index2;
                index2=t;
            }
            while(index1<index2){
                int val1 = array.get(index1);
                int val2 = array.get(index2);
                array.set(index1, val2);
                array.set(index2, val1);
                index1++;
                index2--;
            }
            split();
        }

        public void shiftMutate() {
            Random rd = new Random();
            int index1 = rd.nextInt(cityCount-1);
            int index2 = rd.nextInt(cityCount-1);
            int val=array.get(index1);
            if(index1<index2){
                array.add(index2,val);
                array.remove(index1);
            }else{
                array.remove(index1);
                array.add(index2,val);
            }
            split();
        }
    }
}

