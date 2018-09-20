package example.application;

import org.glassfish.jersey.server.ResourceConfig;

import example.exceptions.CurrencyNotFoundMapper;
import example.exceptions.InternalErrorMapper;
import example.rates.RatesEndPoint;
import example.stronger.StrongerEndPoint;

public class ReactiveJava9Application extends ResourceConfig {

	/**
	 * Register JAX-RS application component
	 */
	public ReactiveJava9Application() {

		register(RatesEndPoint.class);
		register(StrongerEndPoint.class);
		register(CurrencyNotFoundMapper.class);
		register(InternalErrorMapper.class);
	}
}
