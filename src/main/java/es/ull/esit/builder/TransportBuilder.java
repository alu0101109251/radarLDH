package es.ull.esit.builder;

import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;
import es.ull.esit.utilities.Water;

import java.awt.geom.Point2D;

public class TransportBuilder {
    private final Transport transport;

    public TransportBuilder(TransportFactory factory) {
        Point2D[] coordinates = Water.getOceanCoordinates();

        assert coordinates != null;
        Point2D startLocation = coordinates[0];
        Point2D endLocation = coordinates[1];

        transport = factory.createTransport();

        transport.setStartLocation(startLocation.getX(), startLocation.getY());
        transport.setEndLocation(endLocation.getX(), endLocation.getY());
        transport.setPath();
    }

    public Transport getBuild(){
        return transport;
    }
}
