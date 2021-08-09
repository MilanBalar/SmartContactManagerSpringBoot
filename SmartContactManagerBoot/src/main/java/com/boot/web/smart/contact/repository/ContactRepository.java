package com.boot.web.smart.contact.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.boot.web.smart.contact.Entitys.TblContactDetails;

public interface ContactRepository extends CrudRepository<TblContactDetails, Integer> {

	@Query("from TblContactDetails c where c.tblUserLogin.userId=:uId")
	public Page<TblContactDetails> getContactByUserId(@Param("uId") int uId,Pageable pageable);

}
