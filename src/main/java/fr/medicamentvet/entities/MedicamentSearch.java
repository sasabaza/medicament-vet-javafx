package fr.medicamentvet.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.medicamentvet.utils.LocalDateSerializer;
import java.time.LocalDate;
import java.util.List;

/**
 * This class represents the variables of the search form. When a user performs a search, this object saves the variables.
 */
public class MedicamentSearch {

    private final String nomTitulaire;
    private final String numeroAMM;
    private final String formePharmaceutique;
    private final String typeProcedure;
    private final String conditionDelivrance;

    @JsonSerialize(using = LocalDateSerializer.class)
    private final LocalDate debutDate;

    @JsonSerialize(using = LocalDateSerializer.class)
    private final LocalDate finDate;

    private final String substanceActive1;
    private final String substanceActive2;
    private final List<String> especeDestinationList;
    private int skip;

    public MedicamentSearch(String nomTitulaire, String numeroAMM, String formePharmaceutique, String typeProcedure, String conditionDelivrance, LocalDate debutDate, LocalDate finDate, String substanceActive1, String substanceActive2, List<String> especeDestinationList, int skip) {
        this.nomTitulaire = nomTitulaire;
        this.numeroAMM = numeroAMM;
        this.formePharmaceutique = formePharmaceutique;
        this.typeProcedure = typeProcedure;
        this.conditionDelivrance = conditionDelivrance;
        this.debutDate = debutDate;
        this.finDate = finDate;
        this.substanceActive1 = substanceActive1;
        this.substanceActive2 = substanceActive2;
        this.especeDestinationList = especeDestinationList;
        this.skip = skip;
    }

    public String getNomTitulaire() {
        return nomTitulaire;
    }

    public String getNumeroAMM() {
        return numeroAMM;
    }

    public String getFormePharmaceutique() {
        return formePharmaceutique;
    }

    public String getTypeProcedure() {
        return typeProcedure;
    }

    public String getConditionDelivrance() {
        return conditionDelivrance;
    }

    public LocalDate getDebutDate() {
        return debutDate;
    }

    public LocalDate getFinDate() {
        return finDate;
    }

    public String getSubstanceActive1() {
        return substanceActive1;
    }

    public String getSubstanceActive2() {
        return substanceActive2;
    }

    public List<String> getEspeceDestinationList() {
        return especeDestinationList;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    @Override
    public String toString() {
        return "MedicamentSearch{" +
                "nomTitulaire='" + nomTitulaire + '\'' +
                ", numeroAMM='" + numeroAMM + '\'' +
                ", formePharmaceutique='" + formePharmaceutique + '\'' +
                ", typeProcedure='" + typeProcedure + '\'' +
                ", conditionDelivrance='" + conditionDelivrance + '\'' +
                ", debutDate=" + debutDate +
                ", finDate=" + finDate +
                ", substanceActive1='" + substanceActive1 + '\'' +
                ", substanceActive2='" + substanceActive2 + '\'' +
                ", especeDestinationList='" + especeDestinationList + '\'' +
                ", skip=" + skip +
                '}';
    }
}
