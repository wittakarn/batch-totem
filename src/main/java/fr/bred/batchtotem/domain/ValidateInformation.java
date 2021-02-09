package fr.bred.batchtotem.domain;

import java.io.Serializable;

import fr.bred.batchtotem.constant.InputValidateResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    private InputValidateResult validateResult;
    private String message;
}
