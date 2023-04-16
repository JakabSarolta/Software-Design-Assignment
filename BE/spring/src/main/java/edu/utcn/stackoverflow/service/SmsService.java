package edu.utcn.stackoverflow.service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private String accountSid = "AC71dc7db305ade23bdd1acb40ae28b7eb";
    private String authToken = "4d65cdf0d420de7512a150e075e14ae9";
    private String fromPhoneNumber = "+16204104124";

    public void sendSms(String toPhoneNumber, String message) throws TwilioException {
        Twilio.init(accountSid, authToken);
        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), message).create();
    }
}
