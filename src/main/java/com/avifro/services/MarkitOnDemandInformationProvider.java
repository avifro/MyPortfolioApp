package com.avifro.services;

import com.avifro.entities.UpdatesMessage;
import com.avifro.http.AvifroJerseyClient;

import javax.xml.ws.Response;
import java.util.ArrayList;
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
        List<UpdatesMessage> updatesMessages = new ArrayList<>(symbols.size());
        List<Response> responses = symbols.parallelStream()
                                          .map(symbol -> jerseyClient.get("/Quote?" + symbol, null))
                                          .collect(Collectors.toList());

        return updatesMessages;
    }
}
