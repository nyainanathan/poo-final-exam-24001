import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class Paiement {
    private final int id;
    private final double montant;
    private final Instant date;
    private Etudiant etudiant;
}
