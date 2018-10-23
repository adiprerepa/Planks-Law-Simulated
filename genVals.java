/**
 (C) ADITYA PREREPA 9-14-18
 THIS PROJECT USES THE EXTERNAL JFREECHART API.
 VISIT THEM AT : http://www.jfree.org/jfreechart/
 **/

import java.util.LinkedHashMap;

public class genVals  {

    double exponentOfE, exponentOfEtop, exponentOfEbottom, bottom, eConst, topWavelengthConst, topConst, lightConst, lightSpeedInVacuum =299792500.0, e = 2.71828, bVals, temperature;
    double PlancksConstant = 6.626068 * Math.pow(10.0, -34);
    double boltzmaanConst = 1.38066 * Math.pow(10.0, -23);

    public genVals(double temperature) {
        this.temperature = temperature;
    }

    public double genBVals(double wavelength) {
            exponentOfEtop = PlancksConstant * lightSpeedInVacuum;
            exponentOfEbottom = wavelength * temperature * boltzmaanConst;
            exponentOfE = exponentOfEtop / exponentOfEbottom;
            eConst = Math.pow(e, exponentOfE);
            bottom = eConst - 1;
            topWavelengthConst = Math.pow(wavelength, -5);
            lightConst = Math.pow(lightSpeedInVacuum, 2);
            topConst = 2 * PlancksConstant * lightConst * topWavelengthConst;
            bVals = topConst / bottom;
            bVals *= Math.pow(10,-6);
            return bVals;
    }

    public LinkedHashMap<Double, Double> getBVals(double startValWavelength, double endVal, double step) {
        LinkedHashMap<Double, Double> bValsInArray = new LinkedHashMap<Double, Double>();
        //LinkedHashMap for Speed and Accuracy in order
        for (;  startValWavelength<= endVal ; startValWavelength += step) {
            double Val = genBVals(startValWavelength);
            bValsInArray.put(startValWavelength, Val);
        }
        return bValsInArray;
    }










}
