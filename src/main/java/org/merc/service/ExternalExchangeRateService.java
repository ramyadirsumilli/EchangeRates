package org.merc.service;

import org.merc.entity.Exchangerates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ExternalExchangeRateService {
    private static final Logger log = LoggerFactory.getLogger(ExternalExchangeRateService.class);
    private static final String exchangeApiUrl = "https://api.exchangerate-api.com/v4/latest/";
    private final String dailyReport = "C:/temp/dailyReport.xml"; //TODO: add path through application.properties

    public Exchangerates fetchConversionRates() {
        return fetchData(exchangeApiUrl+"EUR");
    }

    public Optional<Exchangerates> fetchPairConversionRates(String baseCode, String targetCode) {
        if (isValid(baseCode) && isValid(targetCode)) {
            return Optional.ofNullable(fetchData(exchangeApiUrl + baseCode + "/" + targetCode));
        }
        return Optional.empty();
    }

    private Exchangerates fetchData(String exchangeApiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        log.info("Fetching exchange rates");
        final ResponseEntity<Exchangerates> responseEntity = restTemplate.exchange(exchangeApiUrl, HttpMethod.GET, entity, Exchangerates.class);
        return responseEntity.getBody();
    }

    private boolean isValid(String input)  {
        String[] currency = {"EUR", "USD", "GBP", "CHF"};
        if (Arrays.stream(currency).anyMatch(input::contains)){
            return true;
        }
        return false;
    }

    public void createDailyReport(Exchangerates exchangerates) {
        try {
            File file = new File(dailyReport);
            JAXBContext jaxbContext = JAXBContext.newInstance(Exchangerates.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            log.info("Creating Daily Report");
            jaxbMarshaller.marshal(exchangerates, file);
            jaxbMarshaller.marshal(exchangerates, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public Optional<Exchangerates> readDailyReport() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Exchangerates.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            log.info("Fetching Daily Report");
            Exchangerates exchangerates = (Exchangerates) jaxbUnmarshaller.unmarshal(new File(dailyReport));
            if (exchangerates != null && exchangerates.getRates() != null) {
                return Optional.of(exchangerates);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
