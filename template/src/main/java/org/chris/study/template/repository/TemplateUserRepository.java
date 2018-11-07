package org.chris.study.template.repository;

import java.util.List;

import org.chris.study.template.entity.TemplateUser;
import org.springframework.data.repository.CrudRepository;

public interface TemplateUserRepository extends CrudRepository<TemplateUser, Long> {

    List<TemplateUser> findByLastName(String lastName);
}
