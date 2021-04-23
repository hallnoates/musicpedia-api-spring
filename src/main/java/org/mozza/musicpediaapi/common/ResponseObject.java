package org.mozza.musicpediaapi.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseObject {

    private int code;
    private Object data;
    private String message;

    public static ResponseObject of(int code) {
        return of(code, null, null);
    }
    public static ResponseObject of(int code, Object data) {
        return new ResponseObject(code, data, null);
    }
    public static ResponseObject of(int code, Object data, String message) {
        return new ResponseObject(code, data, message);
    }
}
