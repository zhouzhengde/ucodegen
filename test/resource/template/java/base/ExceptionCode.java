package ${root.basePackage}.common.exception;

/**
 * 错误code信息
 *
 * @author Bond(China)
 * @version 1.0.0
 */
public enum ExceptionCode {

    SYSTEM_ERROR("10001", "系统错误!"),
    SYSTEM_BUSY("10002", "系统繁忙!"),
    WEBSERVICE_ERROR("10003", "系统调用外部WEB服务调用错误!"),
    DATA_NOT_EXIST("10004", "该数据已经过期或已经删除！"),
    API_INVALID("10005", "该接口已经过期，暂不支持该服务！"),

    PASSWORD_ERROR("30001", "用户名或密码错误!"),
    UN_AUTHORIZED_ERROR("30002", "请重新登入!"),
    ILLEGAL_ACCESS("30003", "非法访问/没有权限!"),
    DISABLED_USER("30004", "用户帐号已禁用！"),

    ILLEGAL_PARAMETER("50001", "非法参数值错误!"),
    ILLEGAL_VALID_CODE("50002", "验证码错误!"),
    ILLEGAL_CONTENT_TYPE("50003", "不是可识别的请求参数体，请仔细阅读接口文档或联系开发人员！"),
    REQUIRED_NOT_NULL("50004", "必填参数不能空，请仔细核对接口要求！"),

    INVALID_TOKEN("70001", "无效TOKEN！"),
    INVALID_NONCE("70002", "无效Nonce!"),
    ILLEGAL_SIGNATURE("70003", "无效的签名！"),
    ILLEGAL_REQUEST_HEADER("70004", "非法请求头，请指定signature!"),

    INVALID_OPT("90001", "操作不生效！"),
    FREQUENCY_ERROR("90002", "请勿重复操作!"),
    UNKNOW_ERROR("90003", "其它未知错误!");

    private String code;

    private String message;

    private ExceptionCode(String value, String message) {
        this.code = value;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
