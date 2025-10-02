package com.yolifay.movieservice.common;

public class ResponseUtil {
    private ResponseUtil() {
        throw new IllegalStateException("Utility Class");
    }
        /**
        * Set response service
        * @param httpStatus HTTP Status Code
        * @param serviceId Service Identifier
        * @param response Constant.RESPONSE Enum
        * @param obj Data Object
        * @return ResponseService
        */
    public static ResponseService setResponse(int httpStatus, String serviceId, Constants.RESPONSE response, Object obj) {
        var res = new ResponseService();
        res.setResponseCode(httpStatus + serviceId + response.getCode());
        res.setResponseDesc(response.getDescription());
        res.setData(obj);
        return res;
    }

    public static ResponseService setResponse(int httpStatus, String serviceId, String caseCode, String description, Object obj) {
        ResponseService res = new ResponseService();
        res.setResponseCode(httpStatus + serviceId + caseCode);
        res.setResponseDesc(description);
        res.setData(obj);
        return res;
    }
}
