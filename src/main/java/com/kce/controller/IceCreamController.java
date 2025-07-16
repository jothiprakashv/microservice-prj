package com.kce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kce.entity.Flavor;
import com.kce.service.IceCreamService;

@RequestMapping("/api/v1/ice")
@RestController
public class IceCreamController {

	private IceCreamService iceCreamService;

	public IceCreamController(@Autowired IceCreamService iceCreamService) {
		this.iceCreamService = iceCreamService;
	}

	@GetMapping
	public List<Flavor> findAllFlavors() {
		return iceCreamService.findAll();
	}
	
	@GetMapping("{id}") 
	public Flavor findFlavorById(@PathVariable long id) {
		return iceCreamService.findById(id);
	}
	
	@PostMapping
	public Flavor addFlavor(@RequestBody Flavor flavor) {
		return iceCreamService.addFlavor(flavor);
	}
	
	@PutMapping("{id}")
	public Flavor updateFlavorById(@RequestBody Flavor flavor, @PathVariable long id) {
		return iceCreamService.updateById(flavor, id);
	}
	
	@DeleteMapping("{id}")
	public Flavor deleteFlavorById(@PathVariable long id) {
		return iceCreamService.deleteById(id);
	}
}


















