package com.application.utility.chapter01_sqlLogging;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.application.utility.data.BrandDTO;

@Mapper
public interface SqlLoggingDAO {

	public List<BrandDTO> getBrandList();
	public BrandDTO getBrandDetail(long brandId);
	public void createBrand(BrandDTO brandDTO);
	public void updateBrand(BrandDTO brandDTO);
	public void deleteBrand(long brandId);
	
}
