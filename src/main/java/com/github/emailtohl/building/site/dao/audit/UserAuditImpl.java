package com.github.emailtohl.building.site.dao.audit;

import org.springframework.stereotype.Repository;

import com.github.emailtohl.building.common.jpa.envers.AbstractAuditedRepository;
import com.github.emailtohl.building.site.entities.User;

/**
 * User的历史信息
 * @author HeLei
 */
@Repository
public class UserAuditImpl extends AbstractAuditedRepository<User> implements UserAudit {

}
