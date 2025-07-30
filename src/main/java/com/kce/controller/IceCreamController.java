package com.kce.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kce.dto.Coupon;
import com.kce.entity.Flavor;
import com.kce.feignclient.CouponFeign;
import com.kce.model.ErrorDetails;
import com.kce.service.IceCreamService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "IceCreamController", description = "Ice Cream CRUD Operations")
@RequestMapping("/api/v1/ice")
@RestController
public class IceCreamController {

	private IceCreamService iceCreamService;
	
	@Autowired
	private CouponFeign couponFeign;

	public IceCreamController(@Autowired IceCreamService iceCreamService) {
		this.iceCreamService = iceCreamService;
	}

	@GetMapping
	public  ResponseEntity<List<Flavor>> findAllFlavors() {
		List<Flavor> _flavors =iceCreamService.findAll();
		return new ResponseEntity<>(_flavors, HttpStatus.OK);
	}
	
	@GetMapping("{id}") 
	public ResponseEntity<Flavor> findFlavorById(@PathVariable long id) {
		Flavor _flavor = iceCreamService.findById(id);
		return new ResponseEntity<>(_flavor, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Flavor> addFlavor(@RequestBody Flavor flavor) {
		Flavor _flavor=iceCreamService.addFlavor(flavor);
		return new ResponseEntity<>(_flavor, HttpStatus.CREATED);

	}
	
	@PutMapping("{id}")
	public ResponseEntity<Flavor> updateFlavorById(@RequestBody Flavor flavor, @PathVariable long id) {
		Flavor _flavor=iceCreamService.updateById(flavor, id);
		return new ResponseEntity<>(_flavor, HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Flavor> deleteFlavorById(@PathVariable long id) {
		Flavor _flavor=iceCreamService.deleteById(id);
		return  new ResponseEntity<>(_flavor, HttpStatus.OK);
	}
	
	@CircuitBreaker(name = "icecream-service", fallbackMethod = "handleError")
	@GetMapping("{id}/{couponCode}")
	public ResponseEntity<Flavor> getProductDiscountByCouponCode(@PathVariable int id,
			@PathVariable String couponCode) {
		Coupon coupon = couponFeign.findByCouponCode(couponCode).getBody();
		Flavor flavor = iceCreamService.findById(id);
		flavor.setPrice(flavor.getPrice() - coupon.getDiscount());
		return new ResponseEntity<Flavor>(flavor, HttpStatus.OK);
	}

	public ResponseEntity<ErrorDetails> handleError(Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


















