package rian.example.quarkusfunction.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

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

	public Uni<String> getCases(String input) {
		return client.get(String.format(resource, input))
				.send()
				.map(
					resp -> {
						return resp.bodyAsString();
					});
	}

}
