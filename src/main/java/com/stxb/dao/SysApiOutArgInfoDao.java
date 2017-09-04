package com.stxb.dao;

import java.util.List;







import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.stxb.model.SysApiOutArgInfo;
/**
 * 
 * @author akku
 *
 */
@Repository
public interface SysApiOutArgInfoDao {
	/**
	 * 保存出参
	 * @param outArgsInfo
	 * @return
	 */
	Integer save(SysApiOutArgInfo outArgsInfo);
	/**
	 * 查询接口下的所有出参
	 * @param apiId 接口ID
	 * @return
	 */
	List<SysApiOutArgInfo> get(int apiId);
	/**
	 * 查询出参
	 * @param id
	 * @return
	 */
	SysApiOutArgInfo getById(int id);
	/**
	 * 更新
	 * @param inArgsInfo
	 * @return
	 */
	Integer update(SysApiOutArgInfo outArgsInfo);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer delete(int id);
	List<SysApiOutArgInfo> getByPyNameAndApiId(@Param("pyName")String pyName,@Param("apiId")int apiId);
}
