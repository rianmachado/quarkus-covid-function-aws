package rian.example.quarkusfunction.model;

import java.util.List;

public class Country {

	private String name;
	private List<CountryDetails> countryDetails;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CountryDetails> getCountryDetails() {
		return countryDetails;
	}
	public void setCountryDetails(List<CountryDetails> countryDetails) {
		this.countryDetails = countryDetails;
	}
	
	

}
