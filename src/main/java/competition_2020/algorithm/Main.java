package competition_2020.algorithm;

import competition_2020.pojo.CarType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static double[][] dis3;
    public static double[] demand3;
    public static int cityCount3;
    public static int maxDis3;
    public static CarType[] carTypes3;
    public static List<Point> points3;
    public static void main(String[] args) throws IOException {
        double[][] dis={
                {0,5,8,7,7,4,12,9,12,6},
                {5,0,4,999,999,999,999,999,999,3},
                {8,4,0,3,999,999,999,999,999,999},
                {7,999,3,0,4,7,999,999,999,999},
                {7,999,999,4,0,3,999,999,999,999},
                {4,999,999,7,3,0,10,999,999,999},
                {12,999,999,999,999,10,0,4,7,999},
                {9,999,999,999,999,999,4,0,5,999},
                {12,999,999,999,999,999,999,7,0,9},
                {6,3,999,999,999,999,999,999,9,0}
        };


        int cityCount=10;
        int maxDis=35;
        double[] demand={
                0,1.7,0.8,1.3,2.8,1.9,3.5,0.9,0.3,1.2
        };
        CarType[] carTypes=new CarType[2];
        carTypes[1]=new CarType(10,2,5);
        carTypes[0]=new CarType(10,5,3);
        double[] arrTime={
            0,30,20,45,80,45,70,10,40,25
        };


        double[][] dis1={
                {0,7.4,12.1,5.3,6.6,8.2,11.9,11.2,10.8},
                {7.4,0,5.8,9,7.1,11.4,12.8,6.9,10.7},
                {12.1,5.8,0,12.2,9.4,10.1,13.7,2.8,9},
                {5.3,9,12.2,0,4.9,4.1,7.8,11.9,8.5},
                {6.6,7.1,9.4,4.9,0,4,6,6.6,4},
                {8.2,11.4,10.1,4.1,4,0,3.9,10,4.4},
                {11.9,12.8,13.7,7.8,6,3.9,0,10.8,5.7},
                {11.2,6.9,2.8,11.9,6.6,10,10.8,0,5.5},
                {10.8,10.7,9,8.5,4,4.4,5.7,5.5,0}
        };

        int cityCount1=9;
        int maxDis1=999;

        double[] demand1={
                0,2,1.5,4.5,3,1.5,4,2.5,3
        };
        CarType[] carTypes1 = new CarType[1];
        carTypes1[0]=new CarType(10,8,999);



        CarType[] carTypes2=new CarType[1];
        carTypes2[0]=new CarType(10,8,5);
        int maxDis2=50;
        int cityCount2=21;

        Point[] points={
                new Point(14.2,13.1),
                new Point(12.8,8.5),
                new Point(18.4,3.4),
                new Point(15.4,16.6),
                new Point(18.9,15.2),
                new Point(15.5,11.6),
                new Point(3.9,10.6),
                new Point(10.6,7.6),
                new Point(8.6,8.4),
                new Point(12.5,2.1),
                new Point(13.8,5.2),
                new Point(6.7,16.9),
                new Point(14.8,2.6),
                new Point(1.8,8.7),
                new Point(17.1,11),
                new Point(7.4,1),
                new Point(0.2,2.8),
                new Point(11.9,19.8),
                new Point(13.2,15.1),
                new Point(6.4,5.6),
                new Point(9.6,14.8),
        };
        double[] demand2={
                0,0.1,0.4,1.2,1.5,0.8,1.3,1.7,0.6,1.2,0.4,0.9,1.3,1.3,1.9,1.7,1.1,1.5,1.6,1.7,1.5
        };
        double[][] dis2=new double[30][30];
        for(int i=0;i<cityCount2;i++){
            for(int j=0;j<cityCount2;j++){
                dis2[i][j]=points[i].getDis(points[j]);
            }
        }



            read();
        dis3=new double[101][101];
        for(int i=0;i<cityCount3;i++){
            for(int j=0;j<cityCount3;j++){
                dis3[i][j]=points3.get(i).getDis(points3.get(j));
            }
        }
        CarType[] carTypes3=new CarType[1];
        carTypes3[0]=new CarType(10,200,999999);
        maxDis3=Integer.MAX_VALUE;

//          CW.cal(carTypes[0],cityCount,demand,dis,maxDis);



        long startTime = System.currentTimeMillis();
        Genatic genatic = new Genatic(carTypes2,cityCount2,dis2,demand2,maxDis2,arrTime);
        genatic.start();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    }

    private static void read() throws IOException {
        BufferedReader bf=new BufferedReader(new FileReader("C:\\Users\\admin\\Desktop\\data.txt"));
        String textLine;
        String str = "";
        while((textLine=bf.readLine())!=null){
            str+="	"+textLine;
        }
        String[] numbers=str.split("	");
        List<Double> num=new ArrayList<>();
        for(int i=0;i<numbers.length;i++){
            String[] s=numbers[i].split(" ");
            for(int j=0;j<s.length;j++){
                try {
                    num.add(Double.parseDouble(s[j]));
                }catch (Exception e){
                    continue;
                }
            }

        }
        points3 = new ArrayList<>();
        cityCount3=101;
        demand3=new double[101];
        for(int i=0;i<101;i++){
            int x = (int)num.get(1+i*7).doubleValue();
            int y = (int)num.get(2+i*7).doubleValue();
            Point point = new Point(x,y);
            points3.add(point);
            demand3[i]=num.get(3+i*7);
        }

//        for(int i=0;i<numbers.length;i++){
//            System.out.println(numbers[i]);
//        }
//        for (int i = 1; i < numbers.length; i++) {
//            number[i]=Double.parseDouble(numbers[i]);
//            System.out.println(number[i]);
//        }
        bf.close();
    }
}

class Point{
    public double x;
    public double y;
    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }
    public double getDis(Point p){
        double xx=this.x-p.x;
        double yy=this.y-p.y;
        return Math.sqrt(xx*xx+yy*yy);
    }
}