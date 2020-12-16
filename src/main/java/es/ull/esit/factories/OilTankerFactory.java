package es.ull.esit.factories;

import es.ull.esit.transports.OilTanker;
import es.ull.esit.transports.Transport;

import java.awt.geom.Point2D;

public class OilTankerFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new OilTanker();
    }
}
