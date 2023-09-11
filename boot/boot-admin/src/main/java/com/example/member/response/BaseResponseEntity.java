package com.example.member.response;

import com.example.member.response.BaseResponseEntity.BaseRes;
import java.net.URI;
import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@Getter
public class BaseResponseEntity extends ResponseEntity<BaseRes> {

    @Getter
    public static class BaseRes {
        private final Boolean succeed;
        private final Object content;
        private final Integer size;

        private BaseRes(Object content) {
            this.succeed = true;
            this.content = content;
            if (content instanceof List) {
                this.size = ((List<?>) content).size();
            } else {
                this.size = 1;
            }
        }
    }
    private BaseResponseEntity(HttpStatusCode status) {
        super(status);
    }

    public static BaseResponseEntity delete() {
        return new BaseResponseEntity(HttpStatus.OK);
    }

    private BaseResponseEntity(BaseRes body, HttpStatusCode status) {
        super(body, status);
    }

    public BaseResponseEntity(BaseRes body, MultiValueMap<String, String> headers,
        HttpStatusCode status) {
        super(body, headers, status);
    }

    public static <T extends BaseResponse> BaseResponseEntity succeed(T response) {
        return new BaseResponseEntity(new BaseRes(response), HttpStatus.OK);
    }

    public static <C extends BaseResponse> BaseResponseEntity succeed(List<C> response) {
        return new BaseResponseEntity(new BaseRes(response), HttpStatus.OK);
    }

    public static <T extends BaseResponse> BaseResponseEntity create(T body, URI uri,
        MultiValueMap<String, String> headers) {
        BodyBuilder created = ResponseEntity.created(uri);
        created.headers(new HttpHeaders(headers));
        ResponseEntity<BaseRes> response = created.body(new BaseRes(body));
        return new BaseResponseEntity(response.getBody(), response.getHeaders(),
            response.getStatusCode());
    }

}
