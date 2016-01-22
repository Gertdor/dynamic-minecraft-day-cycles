/**
 *A utility class for approximating values related to length of a day
 * @author Gertdor
 */
public class CycleUtils{
        /**
         * Approximates the length of a day given the longitude and sunDeclination
         * @param sunDeclination Declination of the sun in degrees from the celestial equator.
         *                       use negative values for south. Must be between -23.5 and 23.5.
         * @param latitude latitude in degrees. use negative values for south
         * @return the length in seconds of the day or -1 if incorrect values
         */
    public static int calculateDayLength(double sunDeclination, double latitude){
        if(Math.abs(sunDeclination)>23.5 || Math.abs(latitude)>90){
            throw new IllegalArgumentException("arguments not within range");
        }
        double sunDeclinationRad = Math.toRadians(sunDeclination);
        double latitudeRad = Math.toRadians(latitude);
        double tao = -1*Math.tan(sunDeclinationRad)*Math.tan(latitudeRad);
        if(Math.abs(tao)>1){
            return 24;
        }
        return((int)(Math.acos(tao)/Math.PI)*24*3600);
    }

    /**
     * Approximates the sun declination between 23.5 degrees south and 23.5 North.
     * Negative values are used instead of south.
     * @param day Day of the year. day 1 is 1st of january. Day 365 is 31th of december.
     * @return sun declination in degrees between -23.5 and 23.5
     */
    public static double approximateSunDeclination(int day){
        int x = (day-79)%365; //days since vernal equinox that is approximated to march 20
                            //Leap years are not taken into account.
        return(23.5*Math.sin((x/365)*Math.PI));
    }

}
