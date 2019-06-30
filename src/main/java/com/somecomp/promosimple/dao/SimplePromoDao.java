package com.somecomp.promosimple.dao;

import java.util.List;

import com.somecomp.promosimple.exception.PromoBusinessException;
import com.somecomp.promosimple.model.PromoSimpleRequest;
import com.somecomp.promosimple.model.PromoSimpleResponse;

public interface SimplePromoDao {
	List<PromoSimpleResponse> create(PromoSimpleRequest request,String xAppCorelationId) throws PromoBusinessException ;
}
