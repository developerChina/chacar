package org.core.service.temporary.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.dao.temporary.TemporaryDao;
import org.core.domain.temporary.TemporaryInfo;
import org.core.service.temporary.QueryService;
import org.core.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
@Service("queryService")
public class QueryServiceImpl implements QueryService {

	@Autowired
	private TemporaryDao temporaryDao;

	@Override
	public List<TemporaryInfo> selectByPage(TemporaryInfo entity, PageModel pageModel, Date startDate, Date endDate) {

		/** 当前需要分页的总数据条数 */
		Map<String, Object> params = new HashMap<>();
		params.put("entity", entity);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		int recordCount = temporaryDao.count(params);
		pageModel.setRecordCount(recordCount);
		if (recordCount > 0) {
			/** 开始分页查询数据：查询第几页的数据 */
			params.put("pageModel", pageModel);
		}
		List<TemporaryInfo> entitys = temporaryDao.selectByPage(params);
		return entitys;

	}

	// 查询中的保存 可以重复发放临时定位仪-->历史数据中的车
	@Override
	public void save(TemporaryInfo entity) {
		// 自动设置日期
		Date date = new Date();
		entity.setCominDate(date);
		// 自动设置为状态2 发放状态
		entity.setType(2);
		temporaryDao.save(entity);
	}
}
