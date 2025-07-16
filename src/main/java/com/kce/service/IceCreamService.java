package com.kce.service;

import java.util.List;

import com.kce.entity.Flavor;

public interface IceCreamService {

	List<Flavor> findAll();

	Flavor findById(long id);

	Flavor addFlavor(Flavor flavor);

	Flavor updateById(Flavor flavor, long id);

	Flavor deleteById(long id);

}
