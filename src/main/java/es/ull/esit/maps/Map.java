package es.ull.esit.maps;

import processing.core.PApplet;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

// https://www.geeksforgeeks.org/creating-interactive-maps-and-geo-visualizations-in-java/

public class Map extends PApplet {

    // Initializing the height and width of the map
    private static final int mapWidth = 350;
    private static final int mapHeight = 500;

    private UnfoldingMap map;

    // Function which implements the unfolds library
    public void setup()
    {
        // Set the Applet window to be
        // 900x600 width and height.
        // The OPENGL argument indicates
        // to use the Processing
        // library's 2D drawing
        this.size(900, 600, P2D);

        // This sets the background colour for the Applet.
        this.background(0, 0, 128);

        // Select a map provider.
        AbstractMapProvider provider = new Google.GoogleTerrainProvider();

        // Creating the map
        map = new UnfoldingMap(
                this, 40, 50, mapWidth,
                mapHeight, provider);

        // This line zooms in and centers the map.
        //map.zoomAndPanTo(zoomLevel, new Location(28.7041f, 77.1025f));

        // This line makes the map interactive as we can zoom in and out.
        MapUtils.createDefaultEventDispatcher(this, map);

        /* TODO: Add markers to the map
            http://unfoldingmaps.org/tutorials/markers-simple
            https://stackoverflow.com/questions/33334835/adding-markers-to-a-unfolding-map-in-java
         */
    }

    // Function to draw the applet window
    public void draw()
    {
        // The draw method is implemented
        // repeatedly by drawing our maps
        // again and again on the canvas
        map.draw();
    }
}
