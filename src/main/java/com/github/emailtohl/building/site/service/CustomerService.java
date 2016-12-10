package com.github.emailtohl.building.site.service;

import static com.github.emailtohl.building.site.entities.Authority.USER_CUSTOMER;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import com.github.emailtohl.building.site.entities.Customer;

/**
 * 为客户管理（CRM）提供服务接口
 * @author HeLei
 */
@Transactional
@Validated
@PreAuthorize("hasAuthority('" + USER_CUSTOMER + "')")
public interface CustomerService {
	/**
	 * 根据用户名和公司进行组合查询
	 * @param title
	 * @param affiliation
	 * @param pageable
	 * @return
	 */
	Page<Customer> query(String name, String title, String affiliation, Pageable pageable);
	
	/**
	 * 获取客户详情
	 * @param id
	 * @return
	 */
	Customer getCustomer(@NotNull @Min(1) Long id);
	
	/**
	 * 修改客户的基本资料
	 * @param id
	 * @param name
	 * @param title
	 * @param affiliation
	 */
	void update(@NotNull @Min(1) Long id, String name, String title, String affiliation);
	
}
