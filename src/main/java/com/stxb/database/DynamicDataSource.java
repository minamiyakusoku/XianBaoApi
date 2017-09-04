package com.stxb.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  


/** 
 * 动态数据源 
 * 实现spring 类方法AbstractRoutingDataSource.determineCurrentLookupKey 
 * 以更新数据源bean 
 * @author danny 
 * @version 1.0 2016-5-10 
 */  
public class DynamicDataSource extends AbstractRoutingDataSource {  
  
  
    @Override  
    protected Object determineCurrentLookupKey() {  
        // 获取数据源标识  
        return DynamicDataSourceHolder.getDataSource();  
    }  
  
  
}  