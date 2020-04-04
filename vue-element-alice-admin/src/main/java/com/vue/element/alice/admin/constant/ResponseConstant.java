package com.vue.element.alice.admin.constant;

/**
 * <p>Description: Response Constant </p>
 *
 * @author rock.jiang
 * Date 2020/01/03 14:27
 */
public class ResponseConstant {
    /**
     * 未授权 状态码
     */
    public static final Integer STATUS_UNAUTHORIZED_CODE = 1000;

    /**
     * 未授权 状态消息
     */
    public static final String STATUS_UNAUTHORIZED_MESSAGE = "未授权！";

    /**
     * 验证签名失败 状态码
     */
    public static final Integer STATUS_VERIFY_SIGNATURE_FAILED_CODE = 1001;

    /**
     * 验证签名失败 状态消息
     */
    public static final String STATUS_VERIFY_SIGNATURE_FAILED_MESSAGE = "非法请求！";
}
