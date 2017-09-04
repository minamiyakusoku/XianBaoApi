package com.stxb.utils.common;

import java.util.ArrayList;
import java.util.List;

import com.stxb.exception.EncodeException;
import com.stxb.exception.FormatException;
import com.stxb.exception.NotnullException;
import com.stxb.model.SysApiInArgInfo;
import com.stxb.model.SysApiOutArgInfo;
/**
 * 参数错误辅助类
 * @author akku
 *
 */
public class ExceptionHelper {
	/**
	 * 入参报错
	 * @param errorNotnullArgs 不能为空的入参集合 
	 * @param errorEncodeArgs 加密/解密失败的入参集合
	 * @param errorFormatArgs 格式化失败的入参集合
	 * @throws NotnullException 非空报错
	 * @throws EncodeException 加密/解密报错报错
	 * @throws FormatException 格式化报错
	 */
	public static void CheckInArgsError(List<SysApiInArgInfo> errorNotnullArgs,List<SysApiInArgInfo> errorEncodeArgs,List<SysApiInArgInfo> errorFormatArgs) throws NotnullException, EncodeException, FormatException{
		if(errorNotnullArgs.size()>0){
			StringBuffer msg = new StringBuffer("入参[");
			for(SysApiInArgInfo i :errorNotnullArgs){
				msg.append(i.getCloName()+"("+i.getPyName()+"),");
			}
			msg.append("]不能为空");
			msg.deleteCharAt(msg.lastIndexOf(","));
			throw new NotnullException(msg.toString());
			
		}
		if(errorEncodeArgs.size()>0){
			StringBuffer msg = new StringBuffer("入参[");
			for(SysApiInArgInfo i :errorEncodeArgs){
				msg.append(i.getCloName()+"("+i.getPyName()+"),");
			}
			msg.append("]解/加密失败");
			msg.deleteCharAt(msg.lastIndexOf(","));
			throw new EncodeException(msg.toString());
			
		}
		
		if(errorFormatArgs.size()>0){
			StringBuffer msg = new StringBuffer("入参[");
			for(SysApiInArgInfo i :errorFormatArgs){
				msg.append(i.getCloName()+"("+i.getPyName()+"),");
			}
			msg.append("]格式化失败");
			msg.deleteCharAt(msg.lastIndexOf(","));
			throw new FormatException(msg.toString());
			
		}
	}
	/**
	 * 
	 * 出参报错
	 * @param errorNotnullArgs 不能为空的入参集合 
	 * @param errorEncodeArgs 加密/解密失败的入参集合
	 * @param errorFormatArgs 格式化失败的入参集合
	 * @throws NotnullException 非空报错
	 * @throws EncodeException 加密/解密报错报错
	 * @throws FormatException 格式化报错
	 */
	public static void CheckOutArgsError(List<SysApiOutArgInfo> errorNotnullArgs,List<SysApiOutArgInfo> errorEncodeArgs,List<SysApiOutArgInfo> errorFormatArgs) throws NotnullException, EncodeException, FormatException{
		if(errorNotnullArgs.size()>0){
			StringBuffer msg = new StringBuffer("出参[");
			msg.append(formatErrorOutArgsList(errorNotnullArgs));
			msg.append("]未查询到");
			throw new NotnullException(msg.toString());
			
		}
		if(errorEncodeArgs.size()>0){
			StringBuffer msg = new StringBuffer("出参[");
			msg.append(formatErrorOutArgsList(errorEncodeArgs));
			msg.append("]加/解密失败");
			throw new EncodeException(msg.toString());
			
		}
		
		if(errorFormatArgs.size()>0){
			StringBuffer msg = new StringBuffer("出参[");
			msg.append(formatErrorOutArgsList(errorFormatArgs));
			msg.append("]格式化失败");
			throw new FormatException(msg.toString());
			
		}
	}
	/**
	 * 出错出参去除重复
	 * @param list
	 * @return
	 */
	public static StringBuffer formatErrorOutArgsList(List<SysApiOutArgInfo> list){
		StringBuffer re = new StringBuffer("");
		List<SysApiOutArgInfo> relist = new ArrayList<SysApiOutArgInfo>();
		relist.addAll(list);
		for(int i = 0 ; i < relist.size()- 1 ;i++)  {       
		      for(int j = relist.size() - 1 ;j > i;j--)  {       
		           if(relist.get(j).getCloName().equals(relist.get(i).getCloName())){       
		        	   relist.remove(j);       
		            }        
		      }        
		}              
		for(SysApiOutArgInfo i :relist){
			re.append(i.getCloName()+"("+i.getPyName()+"),");
		}
		re.deleteCharAt(re.lastIndexOf(","));
		return re;
	}
}
