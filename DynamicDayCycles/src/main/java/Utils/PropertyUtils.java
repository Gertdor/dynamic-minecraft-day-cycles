package Utils;

/** Singleton class to read and write to different property files used by the plugin.
 *
 * @author Bergland
 * @version 0.1
 * @since 2016-01-22
 */
public class PropertyUtils {

    private static PropertyUtils instance = null;

    public static PropertyUtils getInstance(){
        if (instance == null){
            instance = new PropertyUtils();
        }
        return  instance;
    }

    public int getTickLength(){
        return 5;
    }

}
