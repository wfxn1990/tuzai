package com.itheima.domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 封装总条数以及结果集
 * 序列化接口场景以及好处：网络传输、orm框架缓存（内存数据）
 * 将数据写到磁盘（反序列化）--- 数据备份（灾备-容灾）、数据可共享
 */
public class PageResult implements Serializable{

    private Long total; // 总条数
    private List rows;  // 结果集

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
