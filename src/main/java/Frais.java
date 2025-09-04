import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
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
    private Etudiant etudiant;

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

    public FraisStatus getFraisStatus(Instant toCompareTo){
        double totalPaiements= paiementsAssocies.stream()
                .mapToDouble(Paiement::getMontant)
                .sum();
        if(this.deadline.isAfter(toCompareTo) && totalPaiements < this.montant){
            return FraisStatus.IN_PROGRESS;
        } else if (totalPaiements == this.montant){
            return FraisStatus.PAID;
        } else if (this.deadline.isBefore(toCompareTo) && totalPaiements < this.montant){
            return FraisStatus.LATE;
        } else if (totalPaiements >= this.montant){
            return FraisStatus.OVERPAID;
        }
        return FraisStatus.IN_PROGRESS;
    }

    public double getTotalPaye(){
        return  paiementsAssocies.stream()
                .mapToDouble(Paiement::getMontant)
                .sum();
    }
    public double getTotalPaye(Instant t){
        return  paiementsAssocies.stream()
                .filter(p -> p.getDate().isBefore(t))
                .mapToDouble(Paiement::getMontant)
                .sum();
    }

    public double getFraisReste(){
        double totalPaye = paiementsAssocies.stream()
                .mapToDouble(Paiement::getMontant)
                .sum();
        if (totalPaye > this.montant){
            totalPaye = this.montant;
        }
        return this.montant - totalPaye;
    }
}
