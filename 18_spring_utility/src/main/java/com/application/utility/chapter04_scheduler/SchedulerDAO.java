package com.application.utility.chapter04_scheduler;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.utility.data.BrandDTO;
import com.application.utility.data.ProductDTO;

@Mapper
public interface SchedulerDAO {

	public List<ProductDTO> getProductList();
	public List<BrandDTO> getBrandList();
	
}
