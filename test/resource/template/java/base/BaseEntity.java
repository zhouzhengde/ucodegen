package ${root.basePackage}.common.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Base Entity for commonly attributes
 *
 * @author ucodegen
 */
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 4209781713186148057L;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {

        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {

        return updateBy;
    }

    public void setUpdateBy(String updateBy) {

        this.updateBy = updateBy;
    }
}
