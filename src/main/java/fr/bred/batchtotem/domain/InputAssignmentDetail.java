package fr.bred.batchtotem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InputAssignmentDetail extends TransactionDetail {
    private static final long serialVersionUID = 1L;

    public String matricule;
    public String codeCaisse;
    public String nDossierStobi;
    public String mtemis;
    public String mtrecu;
    public String dateDePaiementInitial;
    public String motif;
    public String motifDurap;
}
