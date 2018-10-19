package org.core.service.temporary;

import java.util.Date;
import java.util.List;

import org.core.domain.temporary.TemporaryInfo;
import org.core.util.tag.PageModel;

/**   
 * @Description: 临时定位仪发放
 * 查询服务层接口
 */
public interface QueryService {

	List<TemporaryInfo> selectByPage(TemporaryInfo entity, PageModel pageModel, Date startDate, Date endDate);

	void save(TemporaryInfo entity);

}
