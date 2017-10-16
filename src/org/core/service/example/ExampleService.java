package org.core.service.example;

import java.util.List;

import org.core.domain.Example;

/**
 * Service-》 控制接口
 * @author Administrator
 *
 */
public interface ExampleService {
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	Example findExampleById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	List<Example> findExample(Example example);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeExampleById(Integer id);
	
	/**
	 * 修改用户
	 * @param User 用户对象
	 * */
	void modifyExample(Example example);
	
	/**
	 * 添加
	 * @param User 用户对象
	 * */
	void addExample(Example example);
}
