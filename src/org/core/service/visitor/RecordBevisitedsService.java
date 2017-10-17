package org.core.service.visitor;

import java.util.List;

import org.core.domain.visitor.RecordBevisiteds;


/**
 * Service-》 来访记录被访人列表控制接口
 */
public interface RecordBevisitedsService {
	/**
	 * 添加来访记录被访人列表
	 */
	void save(RecordBevisiteds entity);
	
	/**
	 * 根据id删除来访记录被访人列表
	 */
	void deleteById(String id);
	/**
	 * 修改来访记录被访人列表
	 */
	void update(RecordBevisiteds entity);
	/**
	 * 根据id查询来访记录被访人列表
	 */
	RecordBevisiteds selectById(String id);
	
	/**
	 * 查询来访记录被访人列表(page信息为空不分页)
	 */
	List<RecordBevisiteds> selectByPage(RecordBevisiteds entity);
	
}
