package com.somecomp.promosimple.service;

import java.util.List;

import com.somecomp.promosimple.dao.SimplePromoDao;
import com.somecomp.promosimple.exception.PromoBusinessException;
import com.somecomp.promosimple.model.PromoSimpleRequest;
import com.somecomp.promosimple.model.PromoSimpleResponse;

public class SimplePromoServiceImpl  implements SimplePromoDao{
	
	private final SimplePromoDao simplePromoDao;
	
	
	public SimplePromoServiceImpl(SimplePromoDao simplePromoDao) {
		this.simplePromoDao = simplePromoDao;
	}

	@Override
	public List<PromoSimpleResponse> create(PromoSimpleRequest request,String xAppCorelationId) throws PromoBusinessException{
		List<PromoSimpleResponse> response = simplePromoDao.create(request, xAppCorelationId);
		return response;
	}
}
