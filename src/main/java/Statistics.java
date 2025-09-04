import java.time.Instant;
import java.util.List;

public class Statistics {
    public List<Frais> getLateFees(List<Frais> fraisList, Instant t){
       return fraisList.stream()
               .filter(frais -> frais.getFraisStatus(t) == Frais.FraisStatus.LATE)
               .toList();
    }

    public double getTotalMissingFees(List<Frais> fraisList, Instant t){
        return fraisList.stream()
                .filter(frais -> frais.getFraisStatus(t) == Frais.FraisStatus.LATE)
                .mapToDouble(Frais::getFraisReste)
                .sum();
    }

    public double getTotalPaidByStudent(Etudiant etudiant, List<Frais> fraisList, Instant t){

        List<Frais> paidByStudent = fraisList.stream()
                .filter(frais -> frais.getEtudiant().equals(etudiant))
                .toList();
        double sum = 0;
        for(Frais f :  paidByStudent){
            sum += f.getTotalPaye(t);
        }
        return sum;
    }
}
