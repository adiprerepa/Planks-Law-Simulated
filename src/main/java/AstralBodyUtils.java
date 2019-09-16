import java.util.LinkedHashMap;

/**
 (C) ADITYA PREREPA 9-14-18
 **/

class AstralBodyUtils  {

    private double temperature;
    private double PlancksConstant = 6.626068 * Math.pow(10.0, -34);
    private double boltzmaanConst = 1.38066 * Math.pow(10.0, -23);

    /**
     * @param temperature of astral body
     */
    AstralBodyUtils(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Get the B-Values for a certain wavelength.
     * See README.md for more.
     * Contains algorithm for getting bVals from constants.
     * @param wavelength len
     * @return double
     */
    private double genBVals(double wavelength) {
        double lightSpeedInVacuum = 299792500.0;
        double exponentOfEtop = PlancksConstant * lightSpeedInVacuum;
        double exponentOfEbottom = wavelength * temperature * boltzmaanConst;
        double exponentOfE = exponentOfEtop / exponentOfEbottom;
        double e = 2.71828;
        double eConst = Math.pow(e, exponentOfE);
        double bottom = eConst - 1;
        double topWavelengthConst = Math.pow(wavelength, -5);
        double lightConst = Math.pow(lightSpeedInVacuum, 2);
        double topConst = 2 * PlancksConstant * lightConst * topWavelengthConst;
        double bVals = topConst / bottom;
            bVals *= Math.pow(10,-6);
            return bVals;
    }

    /**
     * Give b-vals in linkedHashMap - use for efficiency
     * @param startValWavelength start length
     * @param endVal end length
     * @param step of graph
     * @return linkedhashmap of bvals
     */
    LinkedHashMap<Double, Double> getBVals(double startValWavelength, double endVal, double step) {
        LinkedHashMap<Double, Double> bValsInArray = new LinkedHashMap<Double, Double>();
        //LinkedHashMap for Speed and Accuracy in order
        for (;  startValWavelength<= endVal ; startValWavelength += step) {
            double Val = genBVals(startValWavelength);
            bValsInArray.put(startValWavelength, Val);
        }
        return bValsInArray;
    }
}
