package com.example.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.AppUtil;

@RestController
@RequestMapping("/payment")

public class PaymentRestController {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentRestController.class);

	@GetMapping("/x")
	public String doPayment() {

		LOG.info("ENTERED INTO PAYMENT PROCESS!");

		try {
			LOG.info("ENTERED ABOUT TO START");
			throw new RuntimeException("NO BALANCE EXCEPTION!");

		} catch (Exception e) {
			LOG.error("UNABLE TO PROCESS" + e.getMessage());
			e.printStackTrace();
			LOG.error("Exception -" + AppUtil.getLogSupport(e));
		}

		return "success";
	}

}
