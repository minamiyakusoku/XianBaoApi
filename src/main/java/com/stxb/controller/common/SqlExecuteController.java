package com.stxb.controller.common;

import com.stxb.ifs.enums.EnumUtils.SqlExecuteMode;
import com.stxb.service.common.SqlExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author akku
 *
 */
@org.springframework.stereotype.Controller
@RequestMapping("/sql")
public class SqlExecuteController {

	@Autowired
	SqlExecuteService ser;

	@ResponseBody
	@RequestMapping(value="/sqlexecute",method=RequestMethod.GET)
	public List sqlExecute(String sql){
//		ApplicationContext ctx=null;
//	    ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
//	    ser=(SqlExecuteService) ctx.getBean("SqlExecuteService");
		
		try {
			return ser.sqlSearch(sql,SqlExecuteMode.SEARCH);
		} catch (SQLException e) {
			return null;
		}
		
//		ApplicationContext ctx=null;
//	    ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
//	    SqlExecuteDao dao=(SqlExecuteDao) ctx.getBean("SqlExecuteDao");
//	    Map<String, String> sqlMap = new HashMap<String, String>();
//	    sqlMap.put("sql", sql);
//	    
//	    return dao.sqlSearch(sqlMap);
	}
}
