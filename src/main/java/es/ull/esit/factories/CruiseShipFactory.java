package es.ull.esit.factories;

import es.ull.esit.transports.CruiseShip;
import es.ull.esit.transports.Transport;

import java.awt.geom.Point2D;

public class CruiseShipFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new CruiseShip();
    }
}
