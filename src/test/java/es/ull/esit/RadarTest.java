package es.ull.esit;

import es.ull.esit.builder.TransportBuilder;
import es.ull.esit.factories.CruiseShipFactory;
import es.ull.esit.factories.FreighterFactory;
import es.ull.esit.factories.OilTankerFactory;
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

    @DisplayName("Testing Factories")
    @Nested
    class FactoryTest {

        private Transport freighter;

        @BeforeEach
        void setUp() {
            TransportFactory factory = new FreighterFactory();
            freighter = factory.createTransport();
        }

        @Test
        @DisplayName("Factory Method")
        void testFactoryMethod() {
            assertAll("Verify the transport is constructed with the factory",
                    () -> assertEquals("Freighter", freighter.getType(), "Transport Type"),
                    () -> assertThrows(NullPointerException.class, ()-> freighter.getLongitude(), "Factory does not set coordinates")
            );
        }
    }

    @DisplayName("Testing Builders")
    @Nested
    class BuilderTest {

        private Transport oilTanker;

        @BeforeEach
        void setUp() {
            TransportBuilder builder = new TransportBuilder(new OilTankerFactory());
            oilTanker = builder.getBuild();
        }

        @Test
        @DisplayName("Factory Method")
        void testFactoryMethod() {
            assertAll("Verify the transport is constructed with the factory",
                    () -> assertEquals("OilTanker", oilTanker.getType(), "Transport Type"),
                    () -> assertTrue(oilTanker.move(), "Builder should set coordinates")
            );
        }
    }


}