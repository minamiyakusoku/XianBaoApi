package com.stxb.dao.common;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;




/**
 * 
 * @author akku
 *
 */
@Repository
public interface SqlExecuteDao {
	public enum SqlExecuteMode{SEARCH,SAVE,DELETE,UPDATE}

	List<Map<Object,Object>> sqlSearch(Map sql);
}
