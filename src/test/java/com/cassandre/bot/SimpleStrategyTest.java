package com.cassandre.bot;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tech.cassandre.trading.bot.dto.market.TickerDTO;
import tech.cassandre.trading.bot.test.mock.TickerFluxMock;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Simple strategy test.
 */
@SpringBootTest
@Import(TickerFluxMock.class)
@DisplayName("Simple strategy test")
public class SimpleStrategyTest {

	@Autowired
	private TickerFluxMock tickerFluxMock;

	/** Dumb strategy. */
	@Autowired
	private SimpleStrategy strategy;

	/**
	 * Check data reception.
	 */
	@Test
	@DisplayName("Check strategy behavioir")
	public void checkStrategy() {
		await().forever().until(() -> tickerFluxMock.isFluxDone());

		// Waiting to see if the strategy received the accounts update.
		await().untilAsserted(() -> assertEquals(strategy.getAccounts().size(), 3));
	}

}
