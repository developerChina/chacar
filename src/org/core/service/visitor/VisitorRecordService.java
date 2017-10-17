package org.core.service.visitor;

import java.util.List;

import org.core.domain.visitor.VisitorRecord;


/**
 * Service-》 来访记录控制接口
 */
public interface VisitorRecordService {
	/**
	 * 添加来访记录
	 */
	void save(VisitorRecord entity);
	
	/**
	 * 根据id删除来访记录
	 */
	void deleteById(String id);
	/**
	 * 修改来访记录
	 */
	void update(VisitorRecord entity);
	/**
	 * 根据id查询来访记录
	 */
	VisitorRecord selectById(String id);
	
	/**
	 * 查询来访记录(page信息为空不分页)
	 */
	List<VisitorRecord> selectByPage(VisitorRecord entity);
	
}
