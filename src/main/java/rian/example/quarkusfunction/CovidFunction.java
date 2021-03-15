package rian.example.quarkusfunction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.funqy.Funq;
import io.smallrye.mutiny.Uni;
import rian.example.quarkusfunction.input.Request;
import rian.example.quarkusfunction.mapper.Mapper;
import rian.example.quarkusfunction.model.CountryDetails;
import rian.example.quarkusfunction.output.Response;
import rian.example.quarkusfunction.service.WebclientService;

public class CovidFunction {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(CovidFunction.class);

	@Inject
	WebclientService webclientService;

	@Inject
	Mapper mapper;

	@Funq
	public Uni<Response> covidFunction(Request request) throws InterruptedException, ExecutionException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

		String retorno = webclientService.getCases(request.getCountry()).subscribe().asCompletionStage().get();

		List<CountryDetails> list = mapper.mapperCountryDetailsToList(retorno);
		LocalDate initial = LocalDate.of(request.getYear(), request.getMonth(), 1);
		LocalDate start = initial.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate end = initial.with(TemporalAdjusters.lastDayOfMonth());

		List<CountryDetails> result = list.stream()
				.filter(filter -> LocalDate.parse(filter.getDate(), formatter).isAfter(start)
						&& LocalDate.parse(filter.getDate(), formatter).isBefore(end))
				.collect(Collectors.toList());

		return Uni.createFrom()
				.item(Response.builder().reportTitle("Evolution of cases in " + request.getCountry()).reportDatails(result).build());
	}

}
