package ua.knu.csc.emagazine.api.errorhandling;

import java.util.Map;

public class BindingResultErrorDTO {

    private Map<String, String> errors;

    public BindingResultErrorDTO(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
