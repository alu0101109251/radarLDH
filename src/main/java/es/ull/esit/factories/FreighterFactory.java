package es.ull.esit.factories;

import es.ull.esit.transports.Freighter;
import es.ull.esit.transports.Transport;

import java.awt.geom.Point2D;

public class FreighterFactory implements TransportFactory {

    @Override
    public Transport createTransport() {
        return new Freighter();
    }
}
