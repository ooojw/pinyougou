package cn.itcast.core.entity;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

    private List rows;  // 结果集
    private Long total; // 总条数

    public PageResult(List rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
