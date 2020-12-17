package es.ull.esit;

import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.TransportFactory;
import es.ull.esit.transports.CruiseShip;
import es.ull.esit.transports.Transport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Radar Application Testing")
public class RadarTest {

    @DisplayName("Testing Transports")
    @Nested
    class TransportTest {

        private Transport cruise;

        @BeforeEach
        void setUp() {
            cruise = new CruiseShip();
            cruise.setStartLocation(28.453323, -16.029710);
            cruise.setEndLocation(30.5678, -24.0485);
            cruise.setPath();
        }

        @Test
        @DisplayName("Class Getters")
        void testTransportGetters() {
            assertAll("Verify all attributes are set correctly",
                    () -> assertEquals("CruiseShip", cruise.getType(), "Transport Type"),
                    () -> assertEquals(28.453323, cruise.getLatitude(), "Transport Latitude"),
                    () -> assertEquals(-16.029710, cruise.getLongitude(), "Transport Longitude")
            );
        }

        @Test
        @DisplayName("Class Methods")
        void testTransportMethods() {
            String expectedToString = "Transport{type='CruiseShip', currentLocation=Point2D.Double[28.453323, -16.02971]}";
            assertAll("Verify we can update a transport location",
                    () -> assertEquals(expectedToString, cruise.toString(), "Transport toString"),
                    () -> assertTrue(cruise.move(), "Moving transport")
            );
        }
    }


}
