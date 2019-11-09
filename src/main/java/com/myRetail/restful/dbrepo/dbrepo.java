package com.myRetail.restful.dbrepo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myRetail.restful.model.productprice;

public interface dbrepo extends JpaRepository<productprice, Integer>{

	
	@Query(value="insert into PRODUCTPRICE values (:id,:curcode,:value)", nativeQuery = true)
	@Modifying
	@Transactional
	int postPrice(@Param("id") int id, @Param("value") double value, @Param("curcode") String curcode );
}
