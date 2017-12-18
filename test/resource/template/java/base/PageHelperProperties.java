package ${root.basePackage}.common.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by bond on 2017/2/25.
 */
@Configuration
@ConfigurationProperties(prefix = "pagehelper")
public class PageHelperProperties {

    private String helperDialect = "${root.database.dialect}";

    private String reasonable = "true";

    private String supportMethodsArguments = "true";

    private String params = "pageNum=start;pageSize=limit;pageSizeZero=zero;reasonable=heli;count=contsql";

    private String offsetAsPageNum = "true";

    private String rowBoundsWithCount = "true";

    private String pageSizeZero = "true";


    public String getHelperDialect() {
        return helperDialect;
    }

    public void setHelperDialect(String helperDialect) {
        this.helperDialect = helperDialect;
    }

    public String getReasonable() {
        return reasonable;
    }

    public void setReasonable(String reasonable) {
        this.reasonable = reasonable;
    }

    public String getSupportMethodsArguments() {
        return supportMethodsArguments;
    }

    public void setSupportMethodsArguments(String supportMethodsArguments) {
        this.supportMethodsArguments = supportMethodsArguments;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getOffsetAsPageNum() {
        return offsetAsPageNum;
    }

    public void setOffsetAsPageNum(String offsetAsPageNum) {
        this.offsetAsPageNum = offsetAsPageNum;
    }

    public String getRowBoundsWithCount() {
        return rowBoundsWithCount;
    }

    public void setRowBoundsWithCount(String rowBoundsWithCount) {
        this.rowBoundsWithCount = rowBoundsWithCount;
    }

    public String getPageSizeZero() {
        return pageSizeZero;
    }

    public void setPageSizeZero(String pageSizeZero) {
        this.pageSizeZero = pageSizeZero;
    }

    public PageInterceptor getPageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", this.getHelperDialect());
        properties.setProperty("offsetAsPageNum", this.getOffsetAsPageNum());
        properties.setProperty("rowBoundsWithCount", this.getRowBoundsWithCount());
        properties.setProperty("pageSizeZero", this.getRowBoundsWithCount());
        properties.setProperty("reasonable", this.getReasonable());
        properties.setProperty("params", this.getParams());
        interceptor.setProperties(properties);
        return interceptor;
    }

}
