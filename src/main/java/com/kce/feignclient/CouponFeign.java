package com.kce.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kce.dto.Coupon;

@FeignClient("COUPON-SERVICE")
public interface CouponFeign {

	@GetMapping("/api/v1/coupon/{couponCode}")
	public ResponseEntity<Coupon> findByCouponCode(@PathVariable String couponCode);

}