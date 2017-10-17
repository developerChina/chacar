package org.core.service.bevisited.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.bevisited.BevisitedInfoDao;
import org.core.dao.bevisited.DepartInfoDao;
import org.core.domain.bevisited.BevisitedInfo;
import org.core.domain.bevisited.DepartInfo;
import org.core.service.bevisited.BevisitedInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("bevisitedInfoService")
public class BevisitedInfoServiceImpl implements BevisitedInfoService{
	@Autowired
	private BevisitedInfoDao dao;
	@Autowired
	private DepartInfoDao departInfoDao;
	@Override
	public void save(BevisitedInfo entity) {
		dao.save(entity);
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
	}

	@Override
	public void update(BevisitedInfo entity) {
		dao.update(entity);
	}

	@Override
	public BevisitedInfo selectById(String id) {
		return dao.selectById(id);
	}

	@Override
	public List<BevisitedInfo> selectByPage(BevisitedInfo entity) {
		return dao.selectByPage(entity);
	}

	@Override
	public List<Map<String, Object>> getBevisitedTree() {
		List<Map<String, Object>> list=new ArrayList<>();
		
		//追加组织
		List<DepartInfo> departInfos=departInfoDao.selectAll();
		Map<String, Object> map=null;
		for (DepartInfo departInfo : departInfos) {
			map=new HashMap<>();
			map.put("id", departInfo.getDeptID());
			map.put("name", departInfo.getDeptName());
			map.put("pid", departInfo.getSupDeptID());
			map.put("pname", departInfo.getSupDeptName());
			map.put("ischeck", false);
			list.add(map);
		}
		
		//追加被访人
		List<BevisitedInfo> bevisitedInfos=dao.selectAll();
		for (BevisitedInfo bevisitedInfo : bevisitedInfos) {
			map=new HashMap<>();
			map.put("id", bevisitedInfo.getBevisitedID());
			map.put("name", bevisitedInfo.getBevisitedName());
			map.put("pid", bevisitedInfo.getDeptID());
			map.put("pname", bevisitedInfo.getDeptName());
			map.put("ischeck", true);
			map.put("door", bevisitedInfo.getBevisitedDoor());
			map.put("floor", bevisitedInfo.getBevisitedFloor());
			map.put("room", bevisitedInfo.getBevisitedRoom());
			map.put("channel", bevisitedInfo.getBevisitedChannel());
			map.put("tel", bevisitedInfo.getBevisitedTel());
			if("0".equals(bevisitedInfo.getBevisitedStatus())){
				map.put("status", "正常");
			}else{
				map.put("status", "其他");
			}
			list.add(map);
		}
		
		return list;
	}
	 
}
