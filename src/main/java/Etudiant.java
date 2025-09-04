import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Map;

@AllArgsConstructor
public class Etudiant {
    private final String id;
    private final String nom;
    private final String prenom;
    private final Instant dateRentree;
    private Map<Groupe, Instant> historiqueGroupe;
}
