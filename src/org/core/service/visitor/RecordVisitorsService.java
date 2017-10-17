package org.core.service.visitor;

import java.util.List;

import org.core.domain.visitor.RecordVisitors;


/**
 * Service-》 来访记录访客列表控制接口
 */
public interface RecordVisitorsService {
	/**
	 * 添加来访记录访客列表
	 */
	void save(RecordVisitors entity);
	
	/**
	 * 根据id删除来访记录访客列表
	 */
	void deleteById(String id);
	/**
	 * 修改来访记录访客列表
	 */
	void update(RecordVisitors entity);
	/**
	 * 根据id查询来访记录访客列表
	 */
	RecordVisitors selectById(String id);
	
	/**
	 * 查询来访记录访客列表(page信息为空不分页)
	 */
	List<RecordVisitors> selectByPage(RecordVisitors entity);
	
}
