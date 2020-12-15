package es.ull.esit.utilities;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class Water {

    public static Point2D getOceanCoordinates() throws IOException {
        String fromFile = FileUtils.readFileToString( new File( "./ocean_coordinates.json" ), "UTF-8" );
        JSONArray coordinates = new JSONArray( fromFile );

        // pull random coordinates from the array:
        int index = (int) (Math.random() * coordinates.length() );
        JSONObject randomCoordinate = coordinates.getJSONObject( index );

        double longitude = randomCoordinate.getDouble( "lo" );
        double latitude = randomCoordinate.getDouble( "la" );

        return new Point2D.Double(latitude, longitude);
    }
}
