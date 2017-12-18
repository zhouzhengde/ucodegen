package ${root.basePackage}.common.entity;

import javax.persistence.MappedSuperclass;
import java.util.List;

/**
 * 树型结构
 * @author ucodegen
 */
@MappedSuperclass
public class TreeBaseEntity<T> extends BaseEntity {

    private String text;

    private List<T> subList;

    private T parent;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<T> getSubList() {
        return subList;
    }

    public void setSubList(List<T> subList) {
        this.subList = subList;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }
}
