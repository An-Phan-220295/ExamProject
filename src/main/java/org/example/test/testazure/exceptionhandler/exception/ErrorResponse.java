package org.example.test.testazure.exceptionhandler.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    String errorCode;
    String message;
}
