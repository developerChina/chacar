package org.core.service.record.impl;

import java.util.Date;
import java.util.List;

import org.core.dao.visitor.RecordVisitorsDao;
import org.core.domain.visitor.RecordVisitors;
import org.core.service.record.RecordVisitorsService;
import org.core.util.GenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("recordVisitorsService")
public class RecordVisitorsServiceImpl implements RecordVisitorsService{
	@Autowired
	private RecordVisitorsDao dao;
	@Override		
	public String save(RecordVisitors entity) {
		String uuid=GenId.UUID();
		entity.setRecordVID(uuid);
		dao.save(entity);
		return uuid;
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(RecordVisitors entity) {
		dao.update(entity);
	}

	@Override
	public RecordVisitors selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<RecordVisitors> selectByEntity(RecordVisitors entity) {
		return dao.selectByEntity(entity);
	}
	
	@Override
	public List<RecordVisitors> selectVisitorByRecordId(String recordid) {
		return dao.selectVisitorByRecordId(recordid);
	}
	@Override
	public void deleteByRecordID(String recordid) {
		dao.deleteByRecordID(recordid);
	}

	@Override
	public List<RecordVisitors> selectVisitorByRID_Statuts(String recordid, int status) {
		return dao.selectVisitorByRID_Statuts(recordid,status);
	}
	
	@Override
	public List<RecordVisitors> selectRecordInfoBycardID_status(String cardid, int status) {
		return dao.selectRecordInfoBycardID_status(cardid,status);
	}

	@Override
	public List<RecordVisitors> selectVisitorByRID_Statuts_audit(String recordid, int status, int audit) {
		return dao.selectVisitorByRID_Statuts_audit(recordid,status,audit);
	}

	@Override
	public List<RecordVisitors> selectRecordInfoBycardID_status_audit(String cardid, int status, int audit) {
		return dao.selectRecordInfoBycardID_status_audit(cardid,status,audit);
	}

	@Override
	public int selectCountByStatus(String whereStatus, Date startDate, Date endDate) {
		if(startDate!=null&&endDate!=null){
			return dao.selectCountByStatus(whereStatus,startDate,endDate);
		}else{
			if(startDate!=null){
				return dao.selectCountByStatusGtDate(whereStatus, startDate);
			}else if(endDate!=null){
				return dao.selectCountByStatusLtDate(whereStatus, endDate);
			}else{
				return dao.selectCountByStatusEqDate(whereStatus, new Date());
			}
		}
	}

	@Override
	public List<String> getRecordIDBycardno_inDate(String cardNo, Date inDate) {
		return dao.getRecordIDBycardno_inDate(cardNo,inDate);
	}

}
