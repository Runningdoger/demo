package competition_2020.algorithm;

import competition_2020.pojo.CarType;

import java.util.ArrayList;

public class CW{
    public static ArrayList<Integer> cal(CarType carType, int cityCount, double[] demands, double[][] dis, double maxDis){
        ArrayList<P> saveDis=new ArrayList<>();
        for(int i=0;i<cityCount;i++){
            for(int j=0;j<i;j++){
                P p=new P();
                p.head=i;
                p.tail=j;
                p.save=dis[i][0]+dis[0][j]-dis[i][j];
                if(p.save==0){
                    continue;
                }
                saveDis.add(p);
            }
        }
        for (int i = 0; i < saveDis.size(); i++)
        {
            for (int j = 0; j < saveDis.size(); j++)
            {
                P x = saveDis.get(i);
                P y = saveDis.get(j);
                P temp;
                if (x.save > y.save) {
                    temp = new P(x.head,x.tail,x.save);
                    saveDis.set(i,saveDis.get(j));
                    saveDis.set(j,temp);
                }
            }
        }
        int[] flag=new int[cityCount];
        for(int i=0;i<cityCount;i++){
            flag[i]=-1;
        }
        ArrayList<Route> routes=new ArrayList<>();
        for(P p: saveDis){
            if(dis[p.head][0] == 0 && dis[p.tail][0] == 0) {
                continue;
            }

            //初始化一条路线
            Route t = new Route();
            if(flag[p.head]==-1 && flag[p.tail]==-1){
                if(t.getWeight()+demands[p.head] + demands[p.tail] <= carType.getCapacity()&&t.getDis()+dis[p.head][0]*2+dis[p.tail][0]*2-p.save<=maxDis){
                    t.setWeight(t.getWeight()+demands[p.head] + demands[p.tail]);
                    t.getRoute().add(p.head);
                    t.getRoute().add(p.tail);
                    flag[p.head]=routes.size();
                    flag[p.tail]=routes.size();
                    t.setDis(t.getDis()+dis[p.head][0]*2+dis[p.tail][0]*2-p.save);
                }
            }
            else if(flag[p.head]==-1 && flag[p.tail]!=-1){
                int index=0;
                Route route = routes.get(flag[p.tail]);
                for(int i=0;i<route.getRoute().size();i++){
                    if(p.tail==route.getRoute().get(i)){
                        index=i;
                        break;
                    }
                }
                double prevDis=0,lastDis=0,bestDis=0;
                int insertIndex=0,prev=0,last=0;
                if(index==0){
                    prev=0;
                }else {
                    prev=index-1;
                }
                if(index==route.getRoute().size()-1){
                    last=0;
                }else{
                    last=index+1;
                }
                prevDis=dis[prev][p.head]+dis[p.tail][last];
                lastDis=dis[prev][p.tail]+dis[p.head][last];
                if(prevDis<lastDis){
                    insertIndex=index;
                    bestDis=dis[prev][p.head]+dis[p.head][p.tail];
                }else{
                    insertIndex=index+1;
                    bestDis=dis[p.tail][p.head]+dis[p.head][last];
                }


                if(route.getWeight()+demands[p.head] <= carType.getCapacity()&&route.getDis()+bestDis<=maxDis){
                    route.getRoute().add(index,p.head);
                    route.setWeight(route.getWeight()+demands[p.head]);
                    flag[p.head] = flag[p.tail];
                    route.setDis(route.getDis()+bestDis);
                }
            }else if(flag[p.head]!=-1 && flag[p.tail]==-1){
                int index=0;
                Route route = routes.get(flag[p.head]);
                for(int i=0;i<route.getRoute().size();i++){
                    if(p.tail==route.getRoute().get(i)){
                        index=i;
                        break;
                    }
                }
                double prevDis=0,lastDis=0,bestDis=0;
                int insertIndex=0,prev=0,last=0;
                if(index==0){
                    prev=0;
                }else {
                    prev=index-1;
                }
                if(index==route.getRoute().size()-1){
                    last=0;
                }else{
                    last=index+1;
                }
                prevDis=dis[prev][p.tail]+dis[p.head][last];
                lastDis=dis[prev][p.head]+dis[p.tail][last];
                if(prevDis<lastDis){
                    insertIndex=index;
                    bestDis=dis[prev][p.tail]+dis[p.tail][p.head];
                }else{
                    insertIndex=index+1;
                    bestDis=dis[p.head][p.tail]+dis[p.tail][last];
                }

                if(route.getWeight()+demands[p.tail] <= carType.getCapacity()&&route.getDis()+bestDis<=maxDis){
                    route.getRoute().add(index,p.tail);
                    route.setWeight(route.getWeight()+demands[p.tail]);
                    flag[p.tail] = flag[p.head];
                    route.setDis(route.getDis()+bestDis);
                }
            }else{
                continue; //都在，则跳过
            }
            if(t.getRoute().size() >= 1){
                routes.add(t);
            }
        }

//        for(int i=0;i<routes.size();i++){
//            System.out.println(routes.get(i));
//        }
        ArrayList<Integer> arr=new ArrayList<>(cityCount);
        for(int i=0;i<routes.size();i++){
            arr.addAll(routes.get(i).getRoute());
        }
        for(int i=1;i<flag.length;i++){
            if(flag[i]==-1){
                arr.add(i);
            }
        }
        return arr;
    }


}

class P{
    public int head;
    public int tail;
    public double save;

    public P(int head, int tail, double save) {
        this.head = head;
        this.tail = tail;
        this.save = save;
    }

    public P() { }

    @Override
    public String toString() {
        return "P{" +
                "head=" + head +
                ", tail=" + tail +
                ", save=" + save +
                '}';
    }
}