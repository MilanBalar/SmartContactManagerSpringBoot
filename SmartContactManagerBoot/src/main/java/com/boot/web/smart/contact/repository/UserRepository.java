package com.boot.web.smart.contact.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.boot.web.smart.contact.Entitys.TblUserLogin;


public interface UserRepository extends CrudRepository<TblUserLogin, Integer> {

	@Query("select u from TblUserLogin u where u.email=:email1")
	public TblUserLogin getUserByEmail(@Param("email1") String email);
}
