package es.ull.esit.factories;

import es.ull.esit.products.CruiseShip;
import es.ull.esit.products.OilTanker;
import es.ull.esit.products.Transport;

public class CruiseShipFactory implements TransportFactory {
    @Override
    public Transport createTransport() {
        return new CruiseShip();
    }
}
