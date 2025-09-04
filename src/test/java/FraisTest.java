import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FraisTest {
    private Frais frais1;
    private Frais frais2;
    private Frais frais3;
    private Frais frais4;
    private List<Paiement> paiementList1;
    private List<Paiement> paiementList2;
    private List<Paiement> paiementList3;
    private List<Paiement> paiementList4;

    @BeforeEach
    void setUp() {
        paiementList1 =List.of( new Paiement(1, 180000d, Instant.now()), new Paiement(2, 10000d, Instant.now()));
        frais1 = new Frais("ECO-OCT-2025", "Ecolage", 190000d, Instant.parse("2025-10-05T23:59:00Z"), paiementList1);

        paiementList2 =List.of( new Paiement(1, 180000d, Instant.now()));
        frais2 = new Frais("ECO-OCT-2025", "Ecolage", 190000d, Instant.parse("2025-10-05T23:59:00Z"), paiementList2);

        paiementList3 =List.of( new Paiement(1, 180000d, Instant.now()));
        frais3 = new Frais("ECO-OCT-2025", "Ecolage", 190000d, Instant.parse("2025-09-01T23:59:00Z"), paiementList3);

        paiementList4 =List.of( new Paiement(1, 180000d, Instant.now()), new Paiement(2, 30000d, Instant.now()));
        frais4 = new Frais("ECO-OCT-2025", "Ecolage", 190000d, Instant.parse("2025-10-05T23:59:00Z"), paiementList4);
    }

    @Test
    void frais_status_actually_gives_status_ok() {
        assertEquals(Frais.FraisStatus.PAID, frais1.getFraisStatus());
        assertEquals(Frais.FraisStatus.IN_PROGRESS, frais2.getFraisStatus());
        assertEquals(Frais.FraisStatus.LATE, frais3.getFraisStatus());
        assertEquals(Frais.FraisStatus.OVERPAID, frais4.getFraisStatus());
    }
}