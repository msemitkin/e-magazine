package ua.knu.csc.emagazine.api.errorhandling;

public class ErrorDTO {

    private Integer statusCode;
    private String message;

    public ErrorDTO(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
