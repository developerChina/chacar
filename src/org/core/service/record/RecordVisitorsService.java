package org.core.service.record;

import java.util.List;

import org.core.domain.visitor.RecordVisitors;


/**
 * Service-》 来访记录访客列表控制接口
 */
public interface RecordVisitorsService {
	/**
	 * 添加来访记录访客列表
	 */
	String save(RecordVisitors entity);
	
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

	/**
	 * 根据记录id查询来访记录访客列表
	 */
	List<RecordVisitors> selectVisitorByRecordId(String recordid);

	/**
	 * 删除所有记录id的访客
	 */
	void deleteByRecordID(String recordid);
	
	/**
	 * 根据记录id和记录单状态
	 * <br/>查询来访记录访客列表
	 */
	List<RecordVisitors> selectVisitorByRID_Statuts(String recordid,int status);
	/**
	 * 根据身份证ID和记录单状态
	 * <br/>查询来访记录访客列表
	 */
	List<RecordVisitors> selectRecordInfoBycardID_status(String cardid, int status);
	
	/**
	 * 根据记录id和记录单状态,审核状态
	 * <br/>查询来访记录访客列表
	 */
	List<RecordVisitors> selectVisitorByRID_Statuts_audit(String recordid,int status,int audit);
	/**
	 * 根据身份证ID和记录单状态 ,审核状态
	 * <br/>查询来访记录访客列表
	 */
	List<RecordVisitors> selectRecordInfoBycardID_status_audit(String cardid, int status,int audit);	
	
}
