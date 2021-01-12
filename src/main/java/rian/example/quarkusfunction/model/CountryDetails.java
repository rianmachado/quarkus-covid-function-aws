package rian.example.quarkusfunction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class CountryDetails {

	private String Country;

	private String CountryCode;

	private String Province;

	private String City;

	private String CityCode;

	private String Lat;

	private String Lon;

	private Integer Cases;

	private String Status;

	private String Date;

	@Override
	public String toString() {
		return "CountryDetails [Country=" + Country + ", CountryCode=" + CountryCode + ", Province=" + Province
				+ ", City=" + City + ", CityCode=" + CityCode + ", Lat=" + Lat + ", Lon=" + Lon + ", Cases=" + Cases
				+ ", Status=" + Status + ", Date=" + Date + "]";
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCityCode() {
		return CityCode;
	}

	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLon() {
		return Lon;
	}

	public void setLon(String lon) {
		Lon = lon;
	}

	public Integer getCases() {
		return Cases;
	}

	public void setCases(Integer cases) {
		Cases = cases;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

}
