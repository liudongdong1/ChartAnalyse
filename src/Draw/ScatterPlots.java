package Draw;


import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JPanel;

import DataManager.DataUtil;
import DataManager.SampleData;
import org.jfree.chart.*;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
public class ScatterPlots extends ApplicationFrame //用于创建简单的应用程序的主框架的基类。帧监听窗口关闭事件，并作出反应，关闭JVM
{

    public ScatterPlots( String s )
    {
        super( s );
        JPanel jpanel = createDemoPanel( );
        //Constructs a Dimension and initializes it to the specified width and specified height.
        jpanel.setPreferredSize(new Dimension( 560 , 370 ) );//设置控件开始显示出来时候的大小
        setContentPane( jpanel );
    }

    private static JFreeChart createChart(DefaultXYDataset xyzdataset )
    {
        JFreeChart jfreechart = ChartFactory.createScatterPlot(
                "AGE vs WEIGHT vs WORK",  // 图形标题
                "Weight",  // X轴说明
                "AGE",    // Y轴说明
                xyzdataset,            // 数据
                PlotOrientation.HORIZONTAL,  ////图表方向，此处为水平方向
                true,  // 是否创建图例
                true, // 是否生成Tooltips
                false); // 是否生产URL链接
        // 设置整个图片的背景色
        //chart.setBackgroundPaint(Color.PINK);
        // 设置图片有边框
        //chart.setBorderVisible(true);
        // 设置报表区域的背景色
        //xyPlot.setBackgroundPaint(Color.lightGray);
        // 设置横 纵坐标网格颜色
        //xyPlot.setDomainGridlinePaint(Color.GREEN);
        //xyPlot.setRangeGridlinePaint(Color.GREEN);
        // 设置横、纵坐标交叉线是否显示
        //xyPlot.setDomainCrosshairVisible(true);
        //xyPlot.setRangeCrosshairVisible(true);
        // 获得图表区域对象
        XYPlot xyplot = ( XYPlot )jfreechart.getPlot( );
        //设置透明度
        xyplot.setForegroundAlpha( 0.65F );
        //XYItemRenderer xyitemrenderer = xyplot.getRenderer( );
       // xyitemrenderer.setSeriesPaint( 0 ,  Color.BLUE);
        //x轴设置
        /*------设置X轴坐标上的文字-----------*/
       // NumberAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN,11));
        /*------设置X轴的标题文字------------*/
       // NumberAxis.setLabelFont(new Font("宋体", Font.PLAIN,12));
        NumberAxis numberaxis = ( NumberAxis )xyplot.getDomainAxis( );
        numberaxis.setLowerMargin( 0.2 );
        numberaxis.setUpperMargin( 0.5 );
        //y轴设置
        NumberAxis numberaxis1 = ( NumberAxis )xyplot.getRangeAxis( );
        numberaxis1.setLowerMargin( 0.8 );
        numberaxis1.setUpperMargin( 0.9 );

        return jfreechart;
    }

    public static DefaultXYDataset createDataset( )
    {
        ArrayList<SampleData> sampleData=new DataUtil().queryTable("tb1");
        Logger logger=Logger.getLogger ("createDataset");
        logger.info("长度:"+String.valueOf(sampleData.size()));
        DefaultXYDataset xydataset = new DefaultXYDataset();
        int i=0;
        int count=1;
        double[] adx = new double[10];
        double[] ady = new double[10];
        double[] adz = new double[10];
        for (SampleData temp:sampleData
                ) {
            ady[i]=temp.getmY();
            adz[i]=temp.getmZ();
            i++;
            if(i%10==0)
            {
                double ad2[][]={ady,adz};
                xydataset.addSeries("series "+String.valueOf(count),ad2);
                count++;
                i=0;
            }

        }


        //DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset();
      //  double ad3[][] = {  ady , adz,adx};
       // defaultxyzdataset.addSeries( "Series 1" , ad3 );

        return xydataset;
    }

    public static JPanel createDemoPanel( )
    {
        JFreeChart jfreechart = createChart( createDataset( ) );
        ChartPanel chartpanel = new ChartPanel( jfreechart );

        chartpanel.setDomainZoomable( true );
        chartpanel.setRangeZoomable( true );

        return chartpanel;
    }

    public static void main( String args[ ] )
    {
        ScatterPlots bubblechart = new ScatterPlots( "Bubble Chart_frame" );

        bubblechart.pack( );
        RefineryUtilities.centerFrameOnScreen( bubblechart );
        bubblechart.setVisible( true );
    }
   /* public static void data(String title,String[] a,String[] b)
    {
        DefaultXYDataset xydataset = new DefaultXYDataset ();

        *//*data[0]代表x轴，data[1]代表y轴*//*
        double[][] data=new double[2][a.length];
        for(int i=0;i<a.length;i++)
        {
            data[0][i]=Double.parseDouble(a[i]);
            data[1][i]=Double.parseDouble(b[i]);
        }
        xydataset.addSeries("牛的无线定位", data);


        *//*在特定位置显示文字注释*//*
        XYTextAnnotation text1 = new XYTextAnnotation("1sss",2, 2);
        XYTextAnnotation text2 = new XYTextAnnotation("2aaa", 4, 4);
        XYTextAnnotation text3 = new XYTextAnnotation("3bbb", 7, 5);

        //ChartFactory获得各种图表的工厂类
        final JFreeChart chart =ChartFactory.createScatterPlot("","","",xydataset,PlotOrientation.VERTICAL,false,false,false);

        // 获得图表区域对象 ，斌添加注释信息
        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.addAnnotation(text1);
        xyplot.addAnnotation(text2);
        xyplot.addAnnotation(text3);

        //绘制表格操作
        ChartFrame frame = new ChartFrame(title,chart);
        frame.pack();
        RefineryUtilities.centerFrameOnScreen(frame);
        frame.setVisible(true);
    }*/

}
/*public class ScatterPlots extends AbstractAnalysis {
    public static void main(String[] args) throws Exception {
        AnalysisLauncher.open(new ScatterPlots());
    }

    @Override
    public void init(){
        int size = 500000;
        float x;
        float y;
        float z;
        float a;

        Coord3d[] points = new Coord3d[size];
        Color[]   colors = new Color[size];

        Random r = new Random();
        r.setSeed(0);

        for(int i=0; i<size; i++){
            x = r.nextFloat() - 0.5f;
            y = r.nextFloat() - 0.5f;
            z = r.nextFloat() - 0.5f;
            points[i] = new Coord3d(x, y, z);
            a = 0.25f;
            colors[i] = new Color(x, y, z, a);
        }

        Scatter scatter = new Scatter(points, colors);
        chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt");
        chart.getScene().add(scatter);
    }
}*/
