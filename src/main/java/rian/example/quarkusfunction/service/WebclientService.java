package rian.example.quarkusfunction.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.amazonaws.services.lambda.runtime.Context;

import io.smallrye.mutiny.Uni;
import io.vertx.core.VertxOptions;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

@ApplicationScoped
public class WebclientService {

	@ConfigProperty(name = "covid.api.url")
	String url;

	@ConfigProperty(name = "covid.api.resource")
	String resource;

	@Inject
	Vertx vertx;

	private WebClient client;

	@PostConstruct
	void initialize() {
		VertxOptions options = new VertxOptions();
		options.setBlockedThreadCheckInterval(32000);
		Vertx.vertx(options);
		client = WebClient.create(vertx,
				new WebClientOptions().setDefaultHost(url).setDefaultPort(443).setSsl(true).setTrustAll(true));
	}

	/*
	 * public Uni<String> getCases(String path, Context context) {
	 * context.getLogger().log("PREPARA QUE LA VEM BALA ************************");
	 * return client.get(String.format(resource,
	 * path)).send().onItem().transform(HttpResponse::bodyAsString); }
	 */

	/*
	 * public Uni<String> getCases(String path, Context context) {
	 * context.getLogger().
	 * log("**************** INICIO getCases  **************************"); return
	 * client.get(String.format(resource, path)).send().onItem().transform(item -> {
	 * context.getLogger().log(item.bodyAsString()); return item.bodyAsString(); });
	 * 
	 * }
	 */

	/*
	 * public Uni<String> getCases(String input, Context context) { return
	 * client.get(String.format(resource, input)).send().map(resp -> {
	 * context.getLogger().
	 * log("**************** CODE IS:  **************************");
	 * context.getLogger().log(Integer.toString(resp.statusCode())); return
	 * resp.bodyAsString(); }); }
	 */

	public Uni<String> getCases(String path, Context context) {
		return client.get(String.format(resource, path)).send().map(resp -> {
			if (resp.statusCode() == 200) {
				return resp.bodyAsString();
			} else {
				return resp.bodyAsString();
			}
		});
	}

}
