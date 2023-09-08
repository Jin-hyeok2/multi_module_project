package com.example.dto.response;

import java.net.URI;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Getter
public class BaseResponseEntity extends ResponseEntity<Object> {

    private BaseResponseEntity(HttpStatusCode status) {
        super(status);
    }

    public static BaseResponseEntity delete() {
        return new BaseResponseEntity(HttpStatus.OK);
    }

    private BaseResponseEntity(Object body, HttpStatusCode status) {
        super(body, status);
    }

    public BaseResponseEntity(Object body, MultiValueMap<String, String> headers,
        HttpStatusCode status) {
        super(body, headers, status);
    }

    public static <T extends BaseResponse> BaseResponseEntity succeed(T response) {
        return new BaseResponseEntity(response, HttpStatus.OK);
    }

    public static <C extends BaseResponse> BaseResponseEntity succeed(List<C> response) {
        return new BaseResponseEntity(response, HttpStatus.OK);
    }

    public static <T extends BaseResponse> BaseResponseEntity create(T body, URI uri,
        MultiValueMap<String, String> headers) {
        BodyBuilder created = ResponseEntity.created(uri);
        created.headers(new HttpHeaders(headers));
        ResponseEntity<T> response = created.body(body);
        return new BaseResponseEntity(response.getBody(), response.getHeaders(),
            response.getStatusCode());
    }

}
