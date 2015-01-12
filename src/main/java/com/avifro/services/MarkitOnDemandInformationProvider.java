package com.avifro.services;

import com.avifro.entities.UpdatesMessage;
import com.avifro.http.AvifroJerseyClient;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by avifro on 1/9/15.
 */
public class MarkitOnDemandInformationProvider implements StockExchangeInformationProvider {

    private final static String BASE_URL = "http://dev.markitondemand.com/Api/v2/Quote?";

    private AvifroJerseyClient jerseyClient = new AvifroJerseyClient(BASE_URL);

    @Override
    public List<UpdatesMessage> checkForUpdatesBySymbols(List<String> symbols) {
        return symbols.parallelStream()
                      .map(symbol -> jerseyClient.get("/Quote?" + symbol, null))
                      .map(response -> createUpdatesMessage(response))
                      .collect(Collectors.toList());
    }

    private UpdatesMessage createUpdatesMessage(Response response) {
        UpdatesMessage updatesMessage = null;
        StockQuote stockQuote = response.readEntity(StockQuote.class);
        
        return updatesMessage;
    }


//    <StockQuote>
//        <Status>SUCCESS</Status>
//        <Name>Hewlett-Packard Co</Name>
//        <Symbol>HPQ</Symbol>
//        <LastPrice>39.93</LastPrice>
//        <Change>-0.74</Change>
//        <ChangePercent>-1.8195229899</ChangePercent>
//        <Timestamp>Mon Jan 12 15:59:00 UTC-05:00 2015</Timestamp>
//        <MSDate>42016.6659722222</MSDate>
//        <MarketCap>73234894260</MarketCap>
//        <Volume>1851706</Volume>
//        <ChangeYTD>40.13</ChangeYTD>
//        <ChangePercentYTD>-0.4983802641</ChangePercentYTD>
//        <High>40.78</High>
//        <Low>39.77</Low>
//        <Open>40.67</Open>
//    </StockQuote>

    private class StockQuote {
        private String symbol;
        private double change;
        private double lastPrice;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getLastPrice() {
            return lastPrice;
        }

        public void setLastPrice(double lastPrice) {
            this.lastPrice = lastPrice;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }
    }
}
