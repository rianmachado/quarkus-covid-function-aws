package rian.example.quarkusfunction.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import rian.example.quarkusfunction.model.CountryDetails;

@ApplicationScoped
public class Mapper {

	public List<CountryDetails> mapperToCountryDetailsList(String response, Context context) {
		List<CountryDetails> list = new ArrayList<>();

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JsonNode rootNode = mapper.createObjectNode();
			rootNode = mapper.readTree(response);
			CountryDetails[] result = mapper.treeToValue(rootNode, CountryDetails[].class);
			list = Arrays.asList(result);
			list.stream().forEach(i -> context.getLogger().log(i.toString()));
		} catch (Exception e) {
			context.getLogger().log(e.toString());
		}
		return list;
	}

}
