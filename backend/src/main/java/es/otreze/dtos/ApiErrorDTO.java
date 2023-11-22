package es.otreze.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ApiErrorDTO implements Serializable {

    private String backendMessage;
    private String message;
    private LocalDateTime timeStamp;
    private String url;
    private String method;
}
