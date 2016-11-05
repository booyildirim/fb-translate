package com.booyildirim.service;

import com.booyildirim.artifact.Contact;
import com.booyildirim.artifact.Message;
import com.booyildirim.controller.MessengerCallbackController;
import com.booyildirim.model.MessageSendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("messengerService")
public class MessengerServiceImpl implements MessengerService {

    private static final String FB_API_MESSENGER_URL = "https://graph.facebook.com/v2.6/me/messages";
    private static final String REQ_URL =
            FB_API_MESSENGER_URL + "?access_token=EAASRIbqlXwMBAHrTfAfLVbWREvrf3wl35SlEhrp1L2LXFtOlJcDu5WkB6CmTBB" +
                    "gSCJBZAyd8jB53BLS1gBhtZBhTbnxiretbDeOIxTpCuA2y2jZCdQGy2Gi08jpCU1dwTP9A0YnaJqas" +
                    "ZCZAX4Qh8lhLLVAf1rXFYZC3JTdfNanwZDZD";

    private static final Logger LOGGER = LoggerFactory.getLogger(MessengerCallbackController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void sendMessage(String message, String recipientId) {

        MessageSendRequest messageSendRequest = new MessageSendRequest();
        Message m = new Message();
        m.setText(message);
        messageSendRequest.setMessage(m);
        Contact contact = new Contact();
        contact.setId(recipientId);
        messageSendRequest.setRecipient(contact);

        LOGGER.info("request: " + messageSendRequest);

        ResponseEntity<String> response = restTemplate.postForEntity(REQ_URL, messageSendRequest, String.class);
        HttpStatus status = response.getStatusCode();
        if (status != HttpStatus.OK) {
            LOGGER.info("eroor: " + status);
        } else {
            String restCall = response.getBody();
            LOGGER.info("body response: " + restCall);
        }

    }
}
