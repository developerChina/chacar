package org.core.service.record;

import java.util.Date;
import java.util.List;

import org.core.domain.visitor.RecordBevisiteds;
import org.core.domain.webapp.Employee;


/**
 * Service-》 来访记录被访人列表控制接口
 */
public interface RecordBevisitedsService {
	/**
	 * 添加来访记录被访人列表
	 */
	String save(RecordBevisiteds entity);
	
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

	/**
	 * 根据记录id查询来访记录被访人
	 */
	RecordBevisiteds selectBevisitedByRecordId(String recordid);
	/**
	 * 删除所有记录id的被访人			
	 */
	void deleteByRecordID(String recordid);

	/**
	 * 获取一天内，一个卡号下的被访问人
	 * @param cardNo
	 * @param optDate
	 * @return
	 */
	List<Employee> selectBycardNo(String cardNo, Date optDate);

	/**
	 * 不分页
	 * @param entity
	 * @return
	 */
	List<RecordBevisiteds> selectByEntity(RecordBevisiteds entity);	
	
	
	/**
	 * 根据recordIDs获取被访问人id
	 * @param recordIDs
	 * @return
	 */
	List<String> getbevisitedIDByrecordIDs(String recordIDs);
	

}
