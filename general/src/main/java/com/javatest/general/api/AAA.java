package com.javatest.general.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AAA<T> extends ApiResponseData<T> {

    private Long count;

    public AAA(T data, Long count) {
        super(data);
        this.count = count;
    }

}
