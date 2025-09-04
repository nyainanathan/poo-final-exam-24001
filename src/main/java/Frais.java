import lombok.AllArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
public class Frais {
    private final String id;
    private final String label;
    private final double montant;
    private final Instant deadline;
}
