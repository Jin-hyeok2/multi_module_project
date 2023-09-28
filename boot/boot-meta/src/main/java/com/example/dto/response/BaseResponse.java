package com.example.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class BaseResponse {

    private final Boolean succeed;
    private final Object content;
    private final Integer size;

    public BaseResponse(Object content) {
        this.succeed = true;
        this.content = content;
        if (content instanceof List) {
            this.size = ((List<?>) content).size();
        } else {
            this.size = 1;
        }
    }

    public BaseResponse(boolean succeed) {
        this.succeed = succeed;
        this.content = null;
        this.size = null;
    }

    public BaseResponse(boolean succeed, String reason) {
        this.succeed = succeed;
        this.content = reason;
        this.size = null;
    }
}
