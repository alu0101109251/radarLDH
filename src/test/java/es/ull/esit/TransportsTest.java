package es.ull.esit;

import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.Transport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transports Testing")
public class TransportsTest {

    /*private ArrayList<Transport> transports;

    @BeforeEach
    void setUp() {

    }*/

    @Test
    @DisplayName("Class Getters")
    void testFlightGetters() {
        TransportFactory factory = new CruiseShipFactory();
        Transport cruise = factory.createTransport();
        cruise.setCoordinates(28.453323, -16.029710);

        assertAll("Verify all attributes are set correctly",
                () -> assertEquals("CruiseShip", cruise.getType(), "Transport Type"),
                () -> assertEquals(28.453323, cruise.getLatitude(), "Transport Latitude"),
                () -> assertEquals(-16.029710, cruise.getLongitude(), "Transport Longitude")
        );
    }
}
