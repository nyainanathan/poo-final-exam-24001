import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
public class Frais {
    public enum FraisStatus{
        IN_PROGRESS,
        PAID,
        LATE,
        OVERPAID
    }
    private final String id;
    private final String label;
    private final double montant;
    private final Instant deadline;
    private List<Paiement> paiementsAssocies;

    public FraisStatus getFraisStatus(){
        double totalPaiements= paiementsAssocies.stream()
                .mapToDouble(Paiement::getMontant)
                .sum();
        if(this.deadline.isAfter(Instant.now()) && totalPaiements < this.montant){
            return FraisStatus.IN_PROGRESS;
        } else if (totalPaiements == this.montant){
            return FraisStatus.PAID;
        } else if (this.deadline.isBefore(Instant.now()) && totalPaiements < this.montant){
            return FraisStatus.LATE;
        } else if (totalPaiements >= this.montant){
            return FraisStatus.OVERPAID;
        }
        return FraisStatus.IN_PROGRESS;
    }
}
