package utility;

/**
 *A utility class for approximating values related to length of a day.
 * @author Gertdor
 */
public class CycleUtils{

    private static CycleUtils instance = null;

    public static CycleUtils getInstance(){
        if (instance == null){
            instance = new CycleUtils();
        }
        return  instance;
    }

        /**
         * Approximates the length of a day given the longitude and sunDeclination
         * for more information visit: http://www.jgiesen.de/astro/solarday.html
         * @param day Day of the year with 1 being 1st of january and 365 the 31st of december
         *            From this the sunDeclination is approximated.
         * @param latitude latitude in degrees. use negative values for south
         * @return the length in seconds of the day or -1 if incorrect values
         */
    public int calculateDayLength(int day, double latitude){
        double sunDeclination = approximateSunDeclination(day);
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
    public double approximateSunDeclination(int day){
        int x = (day-79)%365; //days since vernal equinox that is approximated to march 20
                            //Leap years are not taken into account.
        return(23.5*Math.sin((x/365)*Math.PI));
    }

    /**
     * Method to approximate time of civil/nautical twilight.
     * Returns the time for either dawn/dusk which are of equal length
     * given one specific day. In the case of no night/day to seperate them
     * half of the total twilight time is returned.
     * http://www.gandraxa.com/length_of_day.xml
     * Might not be applicable for minecraft?
     * axis = 23.439 degrees - slowly changing
     * @param latitude latitude in degrees. use negative values for south.
     * @param day Day of the year. day 1 is 1st of january. Day 365 is 31th of december.
     */

    public double calculateTwilightLength(double latitude, int day){
        return 0;
    }
}
