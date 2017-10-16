package org.core.service.example.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.core.dao.ExampleDao;
import org.core.domain.Example;
import org.core.service.example.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**   
 * @Description: 服务层接口实现类
 */

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("exampleService")
public class ExampleServiceImpl implements ExampleService{
	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private ExampleDao exampleDao;
	
	/**
	 * ExampleServiceImpl接口addExample方法实现
	 * @see { ExampleService }
	 * */
	@Override
	public void addExample(Example example) {
		exampleDao.save(example);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Example findExampleById(Integer id) {
		return exampleDao.selectById(id);
	}
	
	@Override
	public void removeExampleById(Integer id) {
		exampleDao.deleteById(id);
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Example> findExample(Example example) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("example", example);

		List<Example> examples = exampleDao.selectByPage(params);

		return examples;
	}

	/**
	 * HrmServiceImpl接口addUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyExample(Example example) {
		exampleDao.update(example);
	}
}
