package org.core.service.webapp;

import java.util.List;

import org.core.domain.webapp.Reson;
import org.core.util.tag.PageModel;

public interface ResonService {
	//获得所有通道，返回Passageway对象的集合
		List<Reson> findReson(Reson reson,PageModel pageModel);
		//删除
		void removeResonById(Integer id);
		//修改
		void modifyReson(Reson reson);
		//根据id查通道
		Reson findResonById(Integer rid);
		//添加通道
		void addReson(Reson reson);
		
		//查询所有访问事由
		List<Reson> findAll();
}
