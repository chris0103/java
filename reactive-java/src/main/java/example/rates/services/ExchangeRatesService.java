package example.rates.services;

import example.model.ExchangeRatesResponse;
import io.reactivex.Single;

public interface ExchangeRatesService {

	Single<ExchangeRatesResponse> getExchangeRates(String base);
}
