package msa.orderserver.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public class ApiResponse<T>{
    private Boolean isSuccess;
    private T response;
    private HttpStatus code;
    private ErrorResponse errorResponse;

    public ApiResponse(Boolean isSuccess, T response, HttpStatus code, ErrorResponse errorResponse) {
        this.isSuccess = isSuccess;
        this.response = response;
        this.code = code;
        this.errorResponse = errorResponse;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public Boolean getIsSuccess() {
        return isSuccess;
    }

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public T getResponse() {
        return response;
    }
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
