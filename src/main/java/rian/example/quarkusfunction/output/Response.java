package rian.example.quarkusfunction.output;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rian.example.quarkusfunction.model.CountryDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {

	private String reportTitle;

	private List<CountryDetails> reportDatails;

}