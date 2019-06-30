package com.somecomp.promosimple.controller;

import com.somecomp.promosimple.exception.PromoBusinessException;
import com.somecomp.promosimple.exception.PromoBusinessProblem;
import com.somecomp.promosimple.exception.PromoBusinessProblemImpl;
import com.somecomp.promosimple.model.PromoSimpleRequest;
import com.somecomp.promosimple.model.PromoSimpleResponse;
import com.somecomp.promosimple.service.SimplePromoService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class SimplePromoController {

	private static final Logger logger = LogManager.getLogger(SimplePromoController.class);

	private final SimplePromoService simplePromoService;

	public SimplePromoController(SimplePromoService simplePromoService) {
		this.simplePromoService = simplePromoService;
	}

	/**
	 * Creates Promo.
	 * @param promo   	The promo to create.
	 * @return          A ResponseEntity that contains the created Promo
	 */
	@PostMapping("/promotion/createSimple")
	public ResponseEntity<?> createSimplePromo(@RequestHeader("x-appCorelationId") String xAppCorelationId,@RequestBody PromoSimpleRequest request) {
		final String METHOD_NAME = "createSimplePromo";
		logger.info("START : {} {}",METHOD_NAME,xAppCorelationId);

		/** create the Promo **/
		List<PromoSimpleResponse> promoSimpleResponseList = null;
		try {
			promoSimpleResponseList = simplePromoService.create(request,xAppCorelationId);
			return ResponseEntity.status(201).body(promoSimpleResponseList);
		} catch (PromoBusinessException pbe) {
			logger.error("{} , {} END , Create Simple Promo with promoId: {} , Errored out with BusinessException {}",METHOD_NAME,xAppCorelationId,request.getPromoId(),pbe);
			/** these are the validation exceptions caught in PL/SQL **/
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pbe.getProblems());			
		}catch(Exception e) {
			/** this will catch any unexpected exceptions and also if DAO throws SQLException which will come as a runtime exception **/
			logger.error("{} , {} END , Create Simple Promo with promoId: {} , Errored out with Unknown EXception {}",METHOD_NAME,xAppCorelationId,request.getPromoId(),e);
			PromoBusinessProblem problem = new PromoBusinessProblemImpl("system error", "Please contact System Administrator");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
		}
			

	
}


}
