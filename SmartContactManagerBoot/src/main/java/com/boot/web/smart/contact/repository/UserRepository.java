package com.boot.web.smart.contact.repository;

import org.springframework.data.repository.CrudRepository;

import com.boot.web.smart.contact.Entitys.TblUserLogin;


public interface UserRepository extends CrudRepository<TblUserLogin, Integer> {

}
