package ${root.basePackage}.common.entity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.io.Serializable;
import java.util.List;

import ${root.serviceException};
import ${root.basePackage}.common.util.Helper;
import ${root.basePackage}.common.json.annotation.JsonPathValue;

import static ${root.basePackage}.common.json.annotation.JsonPathValue.DataType.LIST;
import static ${root.basePackage}.common.json.annotation.JsonPathValue.DataType.OBJECT;
import static ${root.basePackage}.common.json.annotation.JsonPathValue.DataType.VALUE;


/**
 * Pager分页工具助手
 * @author ucodegen
 */
public class Pager<T extends Serializable> extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 8044298572565667680L;

    @JsonPathValue(path = "pageSize", dataType = VALUE)
    private int pageSize = 10;

    @JsonPathValue(path = "pageIndex", dataType = VALUE)
    private int pageIndex = 1;

    @JsonPathValue(path = "total", dataType = VALUE)
    private int total;

    @JsonPathValue(path = "previous", dataType = VALUE)
    private int previous;

    @JsonPathValue(path = "next", dataType = VALUE)
    private int next;

    @JsonPathValue(path = "first", dataType = VALUE)
    private int first;

    @JsonPathValue(path = "last", dataType = VALUE)
    private int last;

    @JsonPathValue(path = "condition", dataType = OBJECT)
    private T condition;

    @JsonPathValue(path = "rows", dataType = LIST)
    private List<T> rows;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public T getCondition() {
        return condition;
    }

    public void setCondition(T condition) {
        this.condition = condition;
    }

    @FunctionalInterface
    public static interface Callback<T> {
        List<T> query(T t) throws ServiceException;
    }

    /**
     * 注意：需要借用Github下的PageHelper工具
     *
     * @param callback
     * @return
     * @throws ServiceException
     */
    public Pager<T> query(Callback<T> callback) throws ServiceException {

        PageHelper.startPage(pageIndex, pageSize);

        Page<T> page = (Page<T>) callback.query(this.getCondition());

        int realTotal = (int) page.getTotal();

        initInfo(realTotal);

        setRows(page.getResult());

        return this;
    }

    /**
     * 注意：需要借用Github下的PageHelper工具
     *
     * @param callback
     * @return
     * @throws ServiceException
     */
    public Pager<T> queryByVO(Callback<? super T> callback) throws ServiceException {

        return query((Callback<T>) callback);
    }

    private void initInfo(Integer total) {

        this.total = total;

        // 设置页大小
        if (Helper.isEqual(0, pageSize)) {
            // 设置默认
            pageSize = 10;
        }

        // 设置当前页
        if (Helper.isEqual(0, pageIndex)) {
            pageIndex = 1;
        }

        // 设置上一页信息
        if (pageIndex > 1) {
            previous = pageIndex - 1;
        } else {
            previous = 1;
        }

        // 总页数
        last = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;

        // 设置下一次索引
        next = pageIndex + 1;
        if (next > last) {
            next = last;
        }

        // 设置头页索引
        first = 1;
    }
}