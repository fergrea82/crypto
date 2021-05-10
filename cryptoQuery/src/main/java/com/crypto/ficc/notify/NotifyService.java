package com.crypto.ficc.notify;

import com.crypto.ficc.model.TickerResult;
import com.crypto.ficc.query.CurrencyQueryExchange;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.Properties;

@Service
@Getter
@Setter
@Slf4j
public class NotifyService {

    public static final int THRESHOLD_VALUE = 5;

    //TODO tidy this class up and break into smaller classes
    @Autowired
    public NotifyService() {
    }

    @Autowired
    private CurrencyQueryExchange currencyQueryExchange;


    public boolean getTickerForCoin(String ccy, String stake) throws InterruptedException {
        log.info("Query for stake: "+stake);
        boolean percentageDifferenceNotFound = true;
        while(percentageDifferenceNotFound) {
            TickerResult newResult = currencyQueryExchange.queryTickerFor(ccy.toUpperCase());
            if (newResult != null && stake != null) {
                ActionEnum buySell;
                Double newPrice = Double.valueOf(newResult.getPrice());
                Float percentageDifference = (newPrice.floatValue()/Double.valueOf(stake).floatValue())*100;
                if (percentageDifference > 100 ) {
                    percentageDifference = percentageDifference -100;
                    buySell = ActionEnum.SELL;
                } else {
                    percentageDifference = 100 - percentageDifference;
                    buySell = ActionEnum.BUY;
                }
                log.info("Direction: "+buySell+" | Current Diff %: "+percentageDifference.toString());
                if (percentageDifference > THRESHOLD_VALUE) {
                    //sendEmail(buySell, newPrice, percentageDifference,ccy);
                    percentageDifferenceNotFound = false;
                }
            }
            Thread.sleep(60000);
        }
        return percentageDifferenceNotFound;
    }

    private void sendEmail(ActionEnum buySell, Double newPrice, Float percentageDifference, String ccy) {
        //TODO figure out better way to send emails
        try {
            Properties emailProperties = new Properties();
            emailProperties.put("mail.smtp.auth", true);
            emailProperties.put("mail.smtp.starttls.enable", "true");
            emailProperties.put("mail.smtp.host", "smtp.gmail.com");
            emailProperties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(emailProperties);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fergrea82@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("fergrea82@gmail.com"));
            message.setSubject(ccy.toUpperCase() + ": Action = " + buySell + " | Current Price: " + newPrice + " | PercentageDifference " + percentageDifference);
            message.setText("You should " + buySell);

            Transport.send(message);
        } catch (Exception e) {
            log.error("Malfunction creating email for"+buySell+":"+newPrice+":"+percentageDifference+":"+ccy,e);
        }
    }
}
