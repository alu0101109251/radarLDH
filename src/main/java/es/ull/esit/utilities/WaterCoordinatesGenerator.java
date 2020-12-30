package es.ull.esit.utilities;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @class WaterCoordinatesGenerator
 * @brief Class to generate random ocean-only coordinates
 *
 * @details This class gets random coordinates from a JSON file and returns the start
 * and destination point.
 *
 */
public class WaterCoordinatesGenerator {

    private static final Logger LOGGER = Logger.getLogger(WaterCoordinatesGenerator.class.getName());     /**< Class Logger. **/

    /**
     * @brief Random ocean coordinates getter
     * @return Point2D[] -> array containing [startPoint, endPoint]
     */
    public static Point2D[] getOceanCoordinates() {
        try {
            String pathname = "./src/main/java/es/ull/esit/utilities/ocean.json";

            String fromFile = FileUtils.readFileToString(new File(pathname),"UTF-8");
            JSONArray coordinates = new JSONArray(fromFile);

            // pull random coordinates from the array:
            SecureRandom r = new SecureRandom();
            int index = r.nextInt(coordinates.length());
            JSONObject randomCoordinate = coordinates.getJSONObject(index);

            double startLatitude = randomCoordinate.getDouble("la1");
            double startLongitude = randomCoordinate.getDouble("lo1");

            double endLatitude = randomCoordinate.getDouble("la2");
            double endLongitude = randomCoordinate.getDouble("lo2");

            Point2D start = new Point2D.Double(startLatitude, startLongitude);
            Point2D end = new Point2D.Double(endLatitude, endLongitude);

            return new Point2D[] { start, end };

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
