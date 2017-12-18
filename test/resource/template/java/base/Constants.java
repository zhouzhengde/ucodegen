package ${root.basePackage}.common.util;

/**
 * 常量类
 *
 * @author Bond(China)
 * @version 1.0.0
 */
public interface Constants {

    // ################ COMMON [START] #################
    String ENCODING = "UTF-8";
    String CONTENT_TYPE = "Content-Type";
    String CONTENT_TYPE_FILE_STREAM = "application/octet-stream;chartset=utf-8";
    // ################ COMMON [END] ###################

    // ################  REST [START] ##################
    String REST_STATUS = "status";
    String REST_MESSAGE = "message";
    String REST_CODE = "code";
    String REST_CAUSES = "causes";
    String REST_OK_MESSAGE = "OK";
    String REST_ERROR_MESSAGE = "ERROR";
    String REST_RESULT = "result";
    int REST_ACK_OK = 200;
    int REST_ACK_ERROR = 500;
    int METHOD_EXEC_OK = 0;
    int METHOD_EXEC_ERROR = 1;
    // ################  REST [END] ####################

}
