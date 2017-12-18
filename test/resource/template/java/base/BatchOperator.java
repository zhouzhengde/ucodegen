package ${root.basePackage}.common.util;

import ${root.basePackage}.common.exception.ServiceException;
import ${root.basePackage}.common.exception.ExceptionCode;

import java.util.List;

/**
 * @author ucodegen
 */
public final class BatchOperator {

    private BatchOperator() {
    }

    /**
     * 批量操作
     *
     * @param pageSize
     * @param list
     * @param callback
     * @param <T>
     * @return
     * @throws ServiceException
     */
    public static <T> int operate(int pageSize, List<T> list, Callback callback) throws ServiceException {

        int total = list.size();
        int pages = total <= pageSize ? 1 : total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        try {
            for (int i = 0; i < pages; i++) {
                if ((i + 1) * pageSize <= total) {
                    callback.execute(list.subList(i * pageSize, (i + 1) * pageSize));
                } else {
                    callback.execute(list.subList(i * pageSize, total));
                }
            }
            return Constants.METHOD_EXEC_OK;
        } catch (Exception e) {
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR, e);
        }
    }

    public static interface Callback<T> {
        void execute(List<T> list);
    }
}
