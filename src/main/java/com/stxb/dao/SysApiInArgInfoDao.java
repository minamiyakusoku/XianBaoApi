package com.stxb.dao;

import java.util.List;






import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stxb.model.SysApiInArgInfo;
@Repository
/**
 * 
 * @author akku
 *
 */
public interface SysApiInArgInfoDao {
	/**
	 * 保存入参
	 * @param inArgsInfo
	 * @return
	 */
	Integer save(SysApiInArgInfo inArgsInfo);
	/**
	 * 查询接口下的所有入参
	 * @param apiId 接口ID
	 * @return
	 */
	List<SysApiInArgInfo> get(int apiId);
	/**
	 * 查询指定入参
	 * @param id
	 * @return
	 */
	SysApiInArgInfo getById(int id);
	/**
	 * 修改
	 * @param inArgsInfo
	 * @return
	 */
	Integer update(SysApiInArgInfo inArgsInfo);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer delete(int id);
	/**
	 * 根据参数名获取指定接口下的参数
	 * @param pyName
	 * @param apiId
	 * @return
	 */
	List<SysApiInArgInfo> getByPyNameAndApiId(@Param("pyName")String pyName,@Param("apiId")int apiId);
}
