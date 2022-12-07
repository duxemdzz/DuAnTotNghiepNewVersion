package com.nevergiveup.datn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevergiveup.datn.entity.Authority;
import com.nevergiveup.datn.entity.Users;

public interface AuthorityDao extends JpaRepository<Authority, Integer>{
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.users IN ?1")
	List<Authority> authoritiesOf(List<Users> users);
}
