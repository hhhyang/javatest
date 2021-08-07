package com.javatest.framework.commons.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ApiStatusCode {

    OK("OK", "success", HttpStatus.OK),
    ACCESS_DENIED("AccessDenied", "Access denied. ", HttpStatus.FORBIDDEN),
    INTERNAL_ERROR("InternalError", "We encountered an internal error. Please try again. ",
            HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ACCESS_KEY_ID("InvalidAccessKeyId", "The Access Key ID you provided does not exist" +
            " in our records. ", HttpStatus.FORBIDDEN),
    INVALID_HTTP_AUTH_HEADER("InvalidHTTPAuthHeader", "The HTTP authorization header is invalid. " +
            "Consult the service documentation for details. ", HttpStatus.BAD_REQUEST),
    INVALID_URI("InvalidURI", "Could not parse the specified URI. ", HttpStatus.BAD_REQUEST),
    INVALID_QUERY_PARAM("InvalidQueryParam", "some http query parameter is error. ", HttpStatus.BAD_REQUEST),
    INVALID_HTTP_REQUEST("InvalidHTTPRequest", "There was an error in the body of your HTTP request. ",
            HttpStatus.BAD_REQUEST),
    INAPPROPRIATE_JSON("InappropriateJSON", "The JSON you provided was well-formed and valid," +
            " but not appropriate for this operation. ", HttpStatus.BAD_REQUEST),
    MALFORMED_JSON("MalformedJSON", "The JSON you provided was not well-formed. ", HttpStatus.BAD_REQUEST),
    INVALID_VERSION("InvalidVersion", "The API version specified was invalid. ", HttpStatus.NOT_FOUND),
    OPT_IN_REQUIRED("OptInRequired", "A subscription for the service is required. ", HttpStatus.FORBIDDEN),
    PRECONDITION_FAILED("PreconditionFailed", "The specified If-Match header doesn't " +
            "match the ETag header. ", HttpStatus.PRECONDITION_FAILED),
    REQUEST_EXPIRED("RequestExpired", "Request has expired", HttpStatus.BAD_REQUEST),
    IDEMPOTENT_PARAMETER_MISMATH("IdempotentParameterMismatch", "The request uses the same" +
            " client token as a previous, but non-identical request. ", HttpStatus.FORBIDDEN),
    SIGNATURE_NOT_MATCH("SignatureDoesNotMatch", "The request signature we calculated " +
            "does not match the signature you provided. Check your Secret Access Key and signing method. " +
            "Consult the service documentation for details. ", HttpStatus.BAD_REQUEST),
    ABNORMAL_DEPENDENCY_SERVICE("AbnormalDependencyService", "responses from dependency services is abnormal. ",
            HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

}
