package edu.utcn.stackoverflow.service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private String accountSid = xxx;
    private String authToken = xxx;
    private String fromPhoneNumber = xxx;

    public void sendSms(String toPhoneNumber, String message) throws TwilioException {
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), message).create();
    }
}
