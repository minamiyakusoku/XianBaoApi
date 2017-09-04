package com.stxb.utils.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiOutArgInfo;
import com.stxb.model.SysApiSql;

/**
 * Sql语句处理帮助类（模版Sql生成，查询语句生成等）
 * @author Souls
 *
 */
public class SqlHelper {

	/**
	 * TODO Sql语句自动生成(针对单表)
	 * 
	 * @author TanXiaoYang
	 * @param inArgs
	 *            入参列表
	 * @param outArgs
	 *            出参列表
	 * @param sql
	 *            原始sql
	 * @return String 生成的Sql语句
	 */
	public static String toSelectSql(List<SysApiInArgInfo> inArgs,
			List<SysApiOutArgInfo> outArgs, SysApiSql sql) {
		// 处理入参列表
		StringBuffer inAgrsStr = new StringBuffer("");// 入参列表结果字串
		for (int i = 0; i < inArgs.size(); i++) {
			inAgrsStr.append(inArgs.get(i).getPyName()).append("=")
					.append("\"${").append(inArgs.get(i).getPyName())
					.append("}\"");
			// 最后一位不追加 ","
			if (i != (inArgs.size() - 1))
				inAgrsStr.append(" and ");
		}
		// 处理出参列表
		StringBuffer outAgrsStr = new StringBuffer("");// 出参列表结果字串
		for (int i = 0; i < outArgs.size(); i++) {
			outAgrsStr.append(outArgs.get(i).getPyName());
			// 最后一位不追加 ","
			if (i != (outArgs.size() - 1))
				outAgrsStr.append(",");
		}
		// 装配原始Sql语句
		String result = sql.getSqlInfo();
		result = result.replace("{inargs}", inAgrsStr.toString());
		result = result.replace("{outargs}", outAgrsStr.toString());
		return result;

	}

	/**
	 * TODO Sql查询语句自动生成
	 * 
	 * @author TanXiaoYang
	 * @param agrs
	 *            参数Map集合
	 * @param sql
	 *            预处理Sql语句
	 * @return String 生成的Sql查询语句
	 */
	public static String toSearchSql(Map<String, String> agrs, String sql) {
		// 设置要处理的Sql语句
		String resultSql = sql;
		// 循环遍历替换语句相符参数
		for (Map.Entry<String, String> entry : agrs.entrySet()) {
			resultSql = resultSql.replace("${" + entry.getKey() + "}", entry
					.getValue().toString());
		}
		return resultSql;
	}

	/**
	 * TODO Sql查询语句自动生成 ForRequest
	 * 
	 * @author TanXiaoYang
	 * @param request
	 * @param sql
	 *            预处理Sql语句
	 * @return String 生成的Sql查询语句
	 */
	public static String toSearchSqlRequest(HttpServletRequest request,
			String sql) {
		// 取出Url内所有参数
		Map<String, String> agrs = new HashMap<String, String>();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			agrs.put(paraName, request.getParameter(paraName));
		}
		// 设置要处理的Sql语句
		String resultSql = sql;
		// 循环遍历替换语句相符参数
		for (Map.Entry<String, String> entry : agrs.entrySet()) {
			resultSql = resultSql.replace("${" + entry.getKey() + "}", entry
					.getValue().toString());
		}
		return resultSql;
	}

	/**
	 * TODO Sql语句自动生成(针对Sql)
	 * 
	 * @author TanXiaoYang
	 * @param inArgs
	 *            入参列表
	 * @param outArgs
	 *            出参列表
	 * @param sql
	 *            原始sql
	 * @return String 生成的Sql语句
	 */
	public static String toSelectSqlEx(List<SysApiInArgInfo> inArgs,
			List<SysApiOutArgInfo> outArgs, SysApiSql sql) {
		String resultSql = sql.getSqlInfo();
		// 处理替换回显别名
		for (int i = 0; i < outArgs.size(); i++) {
			resultSql = resultSql.replace("{outArgName" + i + "}",
					outArgs.get(i).getPyName().toString());
		}
		// 处理替换查询参数
		for (int i = 0; i < inArgs.size(); i++) {
			resultSql = resultSql.replace("{inArg" + i + "}", ("\"{" + inArgs
					.get(i).getPyName().toString())
					+ "}\"");
		}
		return resultSql;
	}

	/**
	 * TODO Sql查询语句自动生成
	 * 
	 * @author TanXiaoYang
	 * @param agrs
	 *            参数Map集合
	 * @param sql
	 *            预处理Sql语句
	 * @return String 生成的Sql查询语句
	 */
	public static String toSearchSqlEx(Map<String, String> agrs, String sql) {
		// 设置要处理的Sql语句
		String resultSql = sql;
		// 循环遍历替换语句相符参数
		for (Map.Entry<String, String> entry : agrs.entrySet()) {
			resultSql = resultSql.replace("{" + entry.getKey() + "}", entry
					.getValue().toString());
		}
		return resultSql;
	}
}
