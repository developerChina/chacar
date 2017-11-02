package org.core.service.visitor;

import java.util.List;

import org.core.domain.visitor.VisitorInfo;
import org.core.domain.webapp.Blacklist;
import org.core.util.tag.PageModel;

public interface VisitorService {

	List<Blacklist> findBlacklist(PageModel pageModel, Blacklist blacklist);

	void remove(Integer id);

	void addBlacklist(Blacklist blacklist);

	

	//查询访客
	List<VisitorInfo> selectByPage(PageModel pageModel, VisitorInfo entity);

	
	
}