# Exchange Rates
### Software Developer Coding Challenge

Simple exchange rates Service stand-alone application with following features:

- Scheduled to call exchange rates for EUR, USD, GBP & CHF against EUR every 2 hours
- Scheduled daily report to update with above values every night at 01:00
- ✨Rest EndPoints ✨

## RESTful Endpoints

- Trigger manual exchange rate fetching process - GET http://localhost:8080
- Read current exchange rate in terms of X/Y - GET http://localhost:8080/pairConversion with Request Params  baseCode and targetCode from above valid list of currencies
- Read the report data in terms of base currency: EUR - GET http://localhost:8080/dailyReport


## Technical Requirements

Uses Java 15 and built as Maven application. Run as Spring Application.