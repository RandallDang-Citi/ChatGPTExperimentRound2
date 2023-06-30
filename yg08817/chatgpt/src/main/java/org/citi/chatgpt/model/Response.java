package org.citi.chatgpt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    String token;
    String status;
}
