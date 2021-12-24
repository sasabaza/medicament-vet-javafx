package fr.medicamentvet.entities;

import java.util.List;

/**
 * This class represents all inputs to choose from when user wishes to modify Medicament object.
 */
public class UpdateForm {

    private List<String> nomTitulaireList;
    private List<String> typeProcedureList;
    private List<String> statutAutorisationList;
    private List<String> formePharmaceutiqueList;
    private List<String> especeDestinationList;
    private List<String> voieAdministrationList;
    private List<String> conditionDelivranceList;
    private List<String> excipientQSPList;
    private List<String> substanceActiveList;

    public UpdateForm() {
        super();
    }

    public UpdateForm(List<String> nomTitulaireList, List<String> typeProcedureList, List<String> statutAutorisationList, List<String> formePharmaceutiqueList, List<String> especeDestinationList, List<String> voieAdministrationList, List<String> conditionDelivranceList, List<String> excipientQSPList, List<String> substanceActiveList) {
        super();
        this.nomTitulaireList = nomTitulaireList;
        this.typeProcedureList = typeProcedureList;
        this.statutAutorisationList = statutAutorisationList;
        this.formePharmaceutiqueList = formePharmaceutiqueList;
        this.especeDestinationList = especeDestinationList;
        this.voieAdministrationList = voieAdministrationList;
        this.conditionDelivranceList = conditionDelivranceList;
        this.excipientQSPList = excipientQSPList;
        this.substanceActiveList = substanceActiveList;
    }

    public List<String> getNomTitulaireList() {
        return nomTitulaireList;
    }

    public List<String> getTypeProcedureList() {
        return typeProcedureList;
    }

    public List<String> getStatutAutorisationList() {
        return statutAutorisationList;
    }

    public List<String> getFormePharmaceutiqueList() {
        return formePharmaceutiqueList;
    }

    public List<String> getEspeceDestinationList() {
        return especeDestinationList;
    }

    public List<String> getVoieAdministrationList() {
        return voieAdministrationList;
    }

    public List<String> getConditionDelivranceList() {
        return conditionDelivranceList;
    }

    public List<String> getExcipientQSPList() {
        return excipientQSPList;
    }

    public List<String> getSubstanceActiveList() {
        return substanceActiveList;
    }
}
