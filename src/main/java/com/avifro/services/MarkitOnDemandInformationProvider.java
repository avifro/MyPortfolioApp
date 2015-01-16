package com.avifro.services;

import com.avifro.entities.UpdatesMessage;
import com.avifro.http.AvifroJerseyClient;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by avifro on 1/9/15.
 */
public class MarkitOnDemandInformationProvider implements StockExchangeInformationProvider {

    private final static String BASE_URL = "http://dev.markitondemand.com/Api/v2";

    private AvifroJerseyClient jerseyClient = new AvifroJerseyClient(BASE_URL);

    @Override
    public List<UpdatesMessage> checkForUpdatesBySymbols(List<String> symbols) {
        Map<String, String> params = new HashMap<>();

        return symbols.parallelStream()
                      .map(symbol -> {
                                      params.put("symbol", symbol);
                                      return jerseyClient.get("/Quote", params, null);})
                      .map(response -> createUpdatesMessage(response))
                      .collect(Collectors.toList());
    }

    private UpdatesMessage createUpdatesMessage(Response response) {
        String stockQuoteString = response.readEntity(String.class);
        try {
            JAXBContext jc = JAXBContext.newInstance(UpdatesMessage.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream inputStream = new ByteArrayInputStream(stockQuoteString.getBytes(StandardCharsets.UTF_8));

            return  (UpdatesMessage)unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new RuntimeException("Couldn't deserialize response to StockQuote object");
        }
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

}
