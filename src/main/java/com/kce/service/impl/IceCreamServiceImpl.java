package com.kce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kce.entity.Flavor;
import com.kce.repository.IceCreamRepository;
import com.kce.service.IceCreamService;
import com.kce.utils.FlavorNotFoundException;

@Service
public class IceCreamServiceImpl implements IceCreamService {

	private IceCreamRepository iceCreamRepository;

	public IceCreamServiceImpl(@Autowired IceCreamRepository iceCreamRepository) {

		this.iceCreamRepository = iceCreamRepository;
	}

	@Override
	public List<Flavor> findAll() {
		List<Flavor> flavors = iceCreamRepository.findAll();

		return flavors;
	}

	@Override
	public Flavor findById(long id) {
		Optional<Flavor> _flavorContainer = iceCreamRepository.findById(id);
		if (_flavorContainer.isPresent()) {
			Flavor _flavor = _flavorContainer.get();
			return _flavor;
		} else {
			throw new FlavorNotFoundException("Flavor not found for the id:" + id);
		}
	}

	@Override
	public Flavor addFlavor(Flavor flavor) {

		return iceCreamRepository.save(flavor);
	}

	@Override
	public Flavor updateById(Flavor flavor, long id) {
		Optional<Flavor> _flavorContainer = iceCreamRepository.findById(id);
		if (_flavorContainer.isPresent()) {
			Flavor _flavor = _flavorContainer.get();
			_flavor.setName(flavor.getName());
			_flavor.setDescription(flavor.getDescription());
			_flavor.setPrice(flavor.getPrice());
			_flavor.setAvailable(flavor.isAvailable());
			iceCreamRepository.save(_flavor);
			return _flavor;
		} else {
			throw new FlavorNotFoundException("Flavor not found for the id:" + id);
		}

	}

	@Override
	public Flavor deleteById(long id) {
		Optional<Flavor> _flavorContainer = iceCreamRepository.findById(id);
		if (_flavorContainer.isPresent()) {
			Flavor _flavor = _flavorContainer.get();
			iceCreamRepository.delete(_flavor);
			return _flavor;
		} else {
			throw new FlavorNotFoundException("Flavor not found for the id:" + id);
		}

	}

}
