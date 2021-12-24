package fr.medicamentvet.entities;

import java.util.List;

/**
 * This class represents the inputs to choose from in the advanced search form. The inputs are based on the existing medicament data.
 */
public class SearchForm {

    private List<String> nomTitulaireList;
    private List<String> typeProcedureList;
    private List<String> formePharmaceutiqueList;
    private List<String> numeroAMMList;
    private List<String> especeDestinationList;
    private List<String> substanceActiveList;
    private List<String> conditionDelivranceList;

    public SearchForm() {
        super();
    }

    public SearchForm(List<String> nomTitulaireList, List<String> typeProcedureList, List<String> formePharmaceutiqueList, List<String> numeroAMMList, List<String> especeDestinationList, List<String> substanceActiveList, List<String> conditionDelivranceList) {
        this.nomTitulaireList = nomTitulaireList;
        this.typeProcedureList = typeProcedureList;
        this.formePharmaceutiqueList = formePharmaceutiqueList;
        this.numeroAMMList = numeroAMMList;
        this.especeDestinationList = especeDestinationList;
        this.substanceActiveList = substanceActiveList;
        this.conditionDelivranceList = conditionDelivranceList;
    }

    public List<String> getNomTitulaireList() {
        return nomTitulaireList;
    }

    public List<String> getTypeProcedureList() {
        return typeProcedureList;
    }

    public List<String> getFormePharmaceutiqueList() {
        return formePharmaceutiqueList;
    }

    public List<String> getNumeroAMMList() {
        return numeroAMMList;
    }

    public List<String> getEspeceDestinationList() {
        return especeDestinationList;
    }

    public List<String> getSubstanceActiveList() {
        return substanceActiveList;
    }

    public List<String> getConditionDelivranceList() {
        return conditionDelivranceList;
    }
}
