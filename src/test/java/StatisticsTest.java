import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StatisticsTest {
    private Statistics statistics = new Statistics();
    private Etudiant etudiant1;
    private Etudiant etudiant2;
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

      Groupe k3 = new Groupe(3, "K3");

      etudiant1 = new Etudiant("STD24001", "ANDRIANAIVOSON", "Ny Aina Nathan", Instant.parse("2024-10-23T10:00:00Z"), Map.of(k3, Instant.parse("2024-10-23T10:00:00Z")));
      paiementList1 =List.of( new Paiement(1, 180000d, Instant.parse("2025-09-04T10:00:00Z"), etudiant1), new Paiement(2, 10000d, Instant.now(), etudiant1));
      frais1 = new Frais("ECO-OCT-2025", "ECOLAGE", 190000d, Instant.parse("2025-10-05T23:00:00Z"), paiementList1, etudiant1);

      paiementList2 =List.of( new Paiement(1, 180000d, Instant.parse("2025-09-04T10:00:00Z"), etudiant1));
      frais2 = new Frais("ECO-OCT-2025", "Ecolage", 190000d, Instant.parse("2025-10-05T23:59:00Z"), paiementList2, etudiant1);

      paiementList3 =List.of( new Paiement(1, 180000d, Instant.parse("2025-09-04T10:00:00Z"),etudiant1));
      frais3 = new Frais("ECO-OCT-2025", "Ecolage", 190000d, Instant.parse("2025-09-01T23:59:00Z"), paiementList3,etudiant1);

      paiementList4 =List.of( new Paiement(1, 180000d, Instant.parse("2025-09-04T10:00:00Z"),etudiant1), new Paiement(2, 30000d, Instant.now(), etudiant1));
      frais4 = new Frais("ECO-OCT-2025", "Ecolage", 190000d, Instant.parse("2025-10-05T23:59:00Z"), paiementList4, etudiant1);
  }

  @Test
    public void get_late_fees_ok(){
      List<Frais> fraisList = List.of(frais1, frais2, frais3, frais4);
      assertEquals(1, statistics.getLateFees(fraisList, Instant.parse("2025-09-04T10:00:00Z")).size());
     assertEquals(2, statistics.getLateFees(fraisList, Instant.parse("2025-12-10T12:00:00Z")).size());
  }

  @Test
    public void get_total_missing_fees_ok(){
      List<Frais> fraisList = List.of(frais1, frais2, frais3, frais4);
      assertEquals(10000d, statistics.getTotalMissingFees(fraisList, Instant.parse("2025-09-04T10:00:00Z")));
      assertEquals(20000d, statistics.getTotalMissingFees(fraisList, Instant.parse("2025-12-10T12:00:00Z")));
  }

  @Test
    public void get_total_paid_by_student_ok(){
      List<Frais> fraisList = List.of(frais1, frais2, frais3, frais4);
      assertEquals(760000d, statistics.getTotalPaidByStudent(etudiant1, fraisList, Instant.parse("2026-09-04T10:00:00Z")));
      assertEquals(0d, statistics.getTotalPaidByStudent(etudiant1, fraisList, Instant.parse("2021-09-04T10:00:00Z")));
  }
}