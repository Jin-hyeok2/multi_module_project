package com.example.dto.response;

import com.example.exception.BaseException;
import com.example.exception.BaseRollbackException;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Getter
public class BaseResponseEntity extends ResponseEntity<BaseResponse> {

    private BaseResponseEntity(HttpStatusCode status) {
        super(status);
    }

    private BaseResponseEntity(BaseResponse body, HttpStatusCode status) {
        super(body, status);
    }

    private BaseResponseEntity(BaseResponse body, MultiValueMap<String, String> headers,
        HttpStatusCode status) {
        super(body, headers, status);
    }

    public static BaseResponseEntity delete() {
        return new BaseResponseEntity(HttpStatus.OK);
    }

    public static BaseResponseEntity succeed(MultiValueMap<String, String> headers) {
        return new BaseResponseEntity(new BaseResponse(true), headers, HttpStatus.OK);
    }

    public static BaseResponseEntity succeed(BaseResponse body,
        MultiValueMap<String, String> headers) {
        return new BaseResponseEntity(body, headers, HttpStatus.OK);
    }

//    public BaseResponseEntity(MultiValueMap<String, String> headers,
//        HttpStatusCode status) {
//        super(headers, status);
//    }

    public static BaseResponseEntity succeed() {
        return new BaseResponseEntity(new BaseResponse(true), HttpStatus.OK);
    }

    public static BaseResponseEntity fail(BaseRollbackException baseRollbackException) {
        return new BaseResponseEntity(new BaseResponse(false, baseRollbackException.getReason()),
            baseRollbackException.getHttpStatus());
    }

    public static BaseResponseEntity fail(BaseException baseRollbackException) {
        return new BaseResponseEntity(new BaseResponse(false, baseRollbackException.getReason()),
            baseRollbackException.getHttpStatus());
    }

    public static BaseResponseEntity fail() {
        return new BaseResponseEntity(new BaseResponse(false), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T extends BaseResponse> BaseResponseEntity succeed(T response) {
        return new BaseResponseEntity(new BaseResponse(response), HttpStatus.OK);
    }

    public static <C extends BaseResponse> BaseResponseEntity succeed(List<C> response) {
        return new BaseResponseEntity(new BaseResponse(response), HttpStatus.OK);
    }

//    public static <T extends BaseResponse> BaseResponseEntity create(T body, URI uri,
//        MultiValueMap<String, String> headers) {
//        BodyBuilder created = ResponseEntity.created(uri);
//        created.headers(new HttpHeaders(headers));
//        ResponseEntity<BaseRes> response = created.body(new BaseRes(body));
//        return new BaseResponseEntity(response.getBody(), response.getHeaders(),
//            response.getStatusCode());
//    }

}
