import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 (C) ADITYA PREREPA 9-14-18
 **/
public class Main extends ApplicationFrame {

    /**
     * Main Constructor for initializing jfreechart and setting the dataset.
     * @param applicationTitle title
     * @param chartTitle chart title
     * @param bValsInArr data vals
     */
    private Main(String applicationTitle, String chartTitle, LinkedHashMap<Double, Double> bValsInArr) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "wavelength[nm]",
                "bValues", createDataset(bValsInArr),PlotOrientation.VERTICAL, true,
                true,false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1280 , 960));
        setContentPane(chartPanel);
    }

    /**
     * Create a dataset for Main
     * @param bVals LinkedHashMap of b Values
     * @return dataset
     */
    private DefaultCategoryDataset createDataset(LinkedHashMap<Double, Double> bVals){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
            for(Map.Entry<Double, Double> entry : bVals.entrySet()) {
                Double bValue = entry.getValue();
                Double wavelen = entry.getKey();
                dataset.addValue(bValue, "Sun", wavelen);
                System.out.println("Wavelength : " + wavelen + "        bVal " + bValue.toString());
            }
        return dataset;
    }

    public static void main(String[] args) {
        //Sun object - with surface temp of 5800
        AstralBodyUtils sun = new AstralBodyUtils(5800);
        //A linkedHashMap orders the keys, as opposed to a normal hashmap, which is completely random. The wavelengths are hardcoded
        LinkedHashMap<Double, Double> bValsSun = sun.getBVals(0.1*Math.pow(10,-6), 3*Math.pow(10,-6), Math.pow(10,-7));
        Main chart = new Main("Planck's Law graph" , "PEEB-1 RESULTS", bValsSun);
        System.out.println("Generating Graph.....");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}

