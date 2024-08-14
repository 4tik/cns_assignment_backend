package cns.example.project_manage.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseModel<T> {
    private int status;
    private String message;
    private T data;

    public ResponseModel() {

    }
    public ResponseModel(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseModel(int status, String message, T data) {
        this(status, message);
        this.data = data;
    }
}