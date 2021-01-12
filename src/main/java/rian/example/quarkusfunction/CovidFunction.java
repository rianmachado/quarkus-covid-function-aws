package rian.example.quarkusfunction;

import javax.inject.Inject;
import javax.inject.Named;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import rian.example.quarkusfunction.input.Request;
import rian.example.quarkusfunction.mapper.Mapper;
import rian.example.quarkusfunction.output.Response;
import rian.example.quarkusfunction.service.WebclientService;

@Named("covidFunction")
public class CovidFunction implements RequestHandler<Request, Response> {

	@Inject
	WebclientService webclientService;

	@Inject
	Mapper mapper;

	@Override
	public Response handleRequest(Request input, Context context) {
		Response response = new Response();

		LambdaLogger logger = context.getLogger();

		webclientService.getCases(input.getName(), context).subscribe().with(item -> {
			logger.log("Enviando.... Total: " + item);
		}, failure -> failure.printStackTrace());

		context.getLogger().log("**************** FIM getCases  **************************");

		/*
		 * result.subscribe().with(item -> { Integer total =
		 * mapper.mapperToCountryDetailsList(item, context).stream() .mapToInt(caseCovid
		 * -> caseCovid.getCases()).sum(); logger.log("Enviando.... Total: " + total);
		 * }, failure -> failure.printStackTrace());
		 */

		/*
		 * result.subscribe().with(item -> { logger.log("Enviando.... Total: " + item);
		 * }, failure -> failure.printStackTrace());
		 */

		try {
			Thread.currentThread().sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setMessage("Sucesso ....");
		return response;
	}

}
