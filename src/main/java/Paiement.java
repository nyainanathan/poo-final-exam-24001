import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class Paiement {
    private final int id;
    private final double montant;
    private final Instant date;

}
