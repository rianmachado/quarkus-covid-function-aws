package rian.example.springcloudfunction;

import org.junit.jupiter.api.Assertions;

import io.quarkus.amazon.lambda.test.LambdaClient;
import rian.example.quarkusfunction.input.Request;
import rian.example.quarkusfunction.output.Response;

//@QuarkusTest
public class CovidFunctionTest {

	//@Test
	public void testSimpleLambdaSuccess() throws Exception {
		Request in = new Request();
		in.setName("south-africa");
		Response out = LambdaClient.invoke(Response.class, in);
		Assertions.assertEquals("Sucesso ....", out.getMessage());
	}

}