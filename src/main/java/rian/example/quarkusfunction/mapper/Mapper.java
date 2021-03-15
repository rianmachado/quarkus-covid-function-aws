package rian.example.quarkusfunction.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import rian.example.quarkusfunction.model.CountryDetails;

@ApplicationScoped
public class Mapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);

	public List<CountryDetails> mapperCountryDetailsToList(String response) {
		List<CountryDetails> list = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			list = mapper.readValue(response, new TypeReference<List<CountryDetails>>(){});

		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage(), e);
		}

		return list;
	}

}
