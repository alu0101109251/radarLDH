package es.ull.esit;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.MarkerManager;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import es.ull.esit.builder.TransportBuilder;
import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.Water;
import processing.core.PApplet;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GeoMap extends PApplet {

    public static final int N_TRANSPORTS = 5;
    private final ArrayList<Transport> transports = new ArrayList<>();

    private MarkerManager<Marker> markerManager;
    private UnfoldingMap map;

    // Function which implements the unfolds library
    public void setup()
    {
        size(800, 600);
        // Select a map provider.
        AbstractMapProvider provider = new Microsoft.AerialProvider();

        // Creating the map
        map = new UnfoldingMap(this, provider);

        // This line makes the map interactive as we can zoom in and out.
        MapUtils.createDefaultEventDispatcher(this, map);

        generateRandomTransports(new CruiseShipFactory());
        generateRandomTransports(new FreighterFactory());
        generateRandomTransports(new OilTankerFactory());

        markerManager = map.getDefaultMarkerManager();
    }

    private void updateMarkers() {
        for(Transport t : transports) {
            Location location = new Location(t.getLatitude(), t.getLongitude());
            SimplePointMarker marker = new SimplePointMarker(location);
            markerManager.addMarker(marker);
            t.move();
        }
    }

    // Function to draw the applet window
    public void draw()
    {
        markerManager.clearMarkers();
        updateMarkers();
        this.background(0, 0, 128);
        map.draw();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateRandomTransports(TransportFactory factory) {
        for(int i = 0; i < N_TRANSPORTS; i++) {

            TransportBuilder builder = new TransportBuilder(factory);

            transports.add(builder.getBuild());
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { GeoMap.class.getName() });
    }
}
