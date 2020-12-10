package es.ull.esit.factories;

import es.ull.esit.transports.CruiseShip;
import es.ull.esit.transports.Transport;

public class CruiseShipFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new CruiseShip();
    }
}
