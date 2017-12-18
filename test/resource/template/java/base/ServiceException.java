package ${root.basePackage}.common.exception;;

/**
 * 业务异常
 *
 * @author ucodegen
 */
public class ServiceException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5509813495434079448L;

    private final String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(ExceptionCode exceptionCode) {
        this(exceptionCode.getCode(), exceptionCode.getMessage());
    }

    public ServiceException(String msg, Exception e) {
        super(msg, e);
        this.code = ExceptionCode.UNKNOW_ERROR.getCode();
    }

    public ServiceException(String code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    public ServiceException(ExceptionCode exceptionCode, Exception e) {
        super(exceptionCode.getMessage(), e);
        this.code = exceptionCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
