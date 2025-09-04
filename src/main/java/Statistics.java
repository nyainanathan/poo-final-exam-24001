import java.time.Instant;
import java.util.List;

public class Statistics {
    public List<Frais> getLateFeess(List<Frais> fraisList, Instant t){
       return fraisList.stream()
               .filter(frais -> frais.getFraisStatus(t) == Frais.FraisStatus.LATE)
               .toList();
    }

    public double getTotalMissingFees(List<Frais> fraisList, Instant t){
        return fraisList.stream()
                .filter(frais -> frais.getFraisStatus() == Frais.FraisStatus.LATE)
                .mapToDouble(Frais::getMontant)
                .sum();
    }

    public double getTotalPaidByStudent(Etudiant etudiant, List<Frais> fraisList, Instant t){
        return fraisList.stream()
                .filter(frais -> frais.getEtudiant().equals(etudiant))
                .mapToDouble(Frais::getMontant)
                .sum();
    }
}
