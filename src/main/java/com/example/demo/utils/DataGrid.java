package com.example.demo.utils;

import java.util.List;

/**
 * 对于easyuI加载datagrid数据表格使用Java设计模式当中的单例模式(静态内部类的方法)
 */
public class DataGrid {

    private static class  DataGridClass{
        private static final  DataGrid datagrid=new DataGrid();
    }
    private int total;
    private List rows;

    private DataGrid(){}

    public static DataGrid getDataGrid(){
       return DataGridClass.datagrid;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
