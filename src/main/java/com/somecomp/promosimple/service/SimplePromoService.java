package com.somecomp.promosimple.service;

import java.util.List;

import com.somecomp.promosimple.exception.PromoBusinessException;
import com.somecomp.promosimple.model.PromoSimpleRequest;
import com.somecomp.promosimple.model.PromoSimpleResponse;

public interface SimplePromoService {
	public List<PromoSimpleResponse> create(PromoSimpleRequest request,String xAppCorelationId) throws PromoBusinessException;
}
