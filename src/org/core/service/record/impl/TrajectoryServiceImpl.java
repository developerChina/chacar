package org.core.service.record.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.visitor.RecordVisitorsDao;
import org.core.dao.visitor.TrajectoryDao;
import org.core.domain.visitor.Trajectory;
import org.core.domain.webapp.Access;
import org.core.domain.webapp.Elevator;
import org.core.domain.webapp.Employee;
import org.core.domain.webapp.Passageway;
import org.core.service.record.RecordBevisitedsService;
import org.core.service.record.RecordVisitorsService;
import org.core.service.record.TrajectoryService;
import org.core.service.webapp.AccessService;
import org.core.service.webapp.ElevatorService;
import org.core.service.webapp.PassagewayService;
import org.core.util.DateUtil;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("trajectoryService")
public class TrajectoryServiceImpl implements TrajectoryService{
	@Autowired
	private TrajectoryDao dao;
	@Autowired
	@Qualifier("passagewayService")
	private PassagewayService passagewayService;// 通道
	@Autowired
	@Qualifier("elevatorService")
	private ElevatorService elevatorService;//电梯
	@Autowired
	@Qualifier("accessService")
	private AccessService accessService;//门禁
	@Autowired
	@Qualifier("recordBevisitedsService")
	private RecordBevisitedsService recordBevisitedsService;//被访人
	
	@Autowired
	@Qualifier("recordVisitorsService")
	private RecordVisitorsService recordVisitorsService;//访问记录
	@Autowired
	@Qualifier("hrmService")
	private org.core.service.webapp.HrmService hrmService;
	
	@Override
	public List<Trajectory> selectByPage(Trajectory entity, PageModel pageModel,Date startDate,Date endDate) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("entity", entity);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		int recordCount = dao.count(params);
		pageModel.setRecordCount(recordCount);
		if(recordCount > 0){
		    params.put("pageModel", pageModel);
	    }
		List<Trajectory> entitys = dao.selectByPage(params);
		for (Trajectory trajectory : entitys) {
			//设置控制器描述
			String sn=trajectory.getControllerSN();
			int no=Integer.parseInt(trajectory.getDoorNo());
			StringBuffer controllerDesc=new StringBuffer();
			List<Passageway> ways=passagewayService.selectBySN_No(sn, no);
			for (Passageway passageway : ways) {
				controllerDesc.append(passageway.getPassagewayName());
			}
			List<Elevator> elts=elevatorService.selectBySN(sn);
			for (Elevator elevator : elts) {
				controllerDesc.append(elevator.getElevatorName());
			}
			List<Access> as=accessService.selectBySN_No(sn, no);
			for (Access access : as) {
				controllerDesc.append(access.getAccessname());
			}
			trajectory.setControllerDesc(controllerDesc.toString());
			//设置被访问信息
			try {
				if(trajectory.getRecordVisitors()!=null){
					//分开查询
					//List<Employee> employees=recordBevisitedsService.selectBycardNo(trajectory.getRecordVisitors().getCardNo(),DateUtil.StringToDate(trajectory.getOptDate(), "yyyy-MM-dd HH:mm:ss"));
					List<String> recordIDs=recordVisitorsService.getRecordIDBycardno_inDate(trajectory.getRecordVisitors().getCardNo(),DateUtil.StringToDate(trajectory.getOptDate(), "yyyy-MM-dd HH:mm:ss"));
					StringBuffer ridsb = new StringBuffer();
					for(int i = 0; i < recordIDs.size(); i++){
						ridsb.append(",'"+recordIDs.get(i)+"'");
					}
					String rids = ridsb.toString().length()>0?ridsb.toString().substring(1):"''";
					List<String> bevisitedIDs= recordBevisitedsService.getbevisitedIDByrecordIDs(rids);
					StringBuffer bvidsb = new StringBuffer();
					for(int i = 0; i < bevisitedIDs.size(); i++){
						bvidsb.append(",'"+bevisitedIDs.get(i)+"'");
					}
					String bvids = bvidsb.toString().length()>0?bvidsb.toString().substring(1):"''";
					List<Employee> employees=hrmService.getEmployeeByIds(bvids);
					trajectory.setEmployees(employees);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return entitys;
	}
	@Override
	public void deleteTrajectoryById(String id) {
		dao.deleteTrajectoryById(id);
	}

}
