package com.booyildirim.controller;

import com.booyildirim.artifact.FBMessengerApiRequest;
import com.booyildirim.artifact.MessengerEntry;
import com.booyildirim.model.BaseResponse;
import com.booyildirim.service.MessengerService;
import com.booyildirim.service.TranslatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

@RestController
public class MessengerCallbackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessengerCallbackController.class);

    @Autowired
    private MessengerService messengerService;

    @Autowired
    private TranslatorService translatorService;

    @RequestMapping(method = RequestMethod.GET, value = "/health")
    public String test() {
        return "oh yeah, i am healty!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/init")
    public BaseResponse openingMessage() {

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(true);
        baseResponse.setAppMessage(translatorService.usage());

        return baseResponse;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/webhook", produces = "application/json")
    public void translate(@RequestBody FBMessengerApiRequest request) {

        if (request != null) {

            for (MessengerEntry messengerEntry : request.getEntry()) {
                if (messengerEntry.getMessaging().length == 1) { // only single line message requests are accepted
                    if (messengerEntry.getMessaging()[0] != null && messengerEntry.getMessaging()[0].getMessage() != null) {
                        final String message = messengerEntry.getMessaging()[0].getMessage().getText();

                        if (message != null) {
                            final String[] tokens = message.split(" ");

                            if (tokens.length >= 3) {
                                final String src = tokens[0];
                                final String dest = tokens[1];

                                String text = "";

                                for (int i = 2; i < tokens.length; i++) {
                                    text += tokens[i];
                                    text += " ";
                                }

                                final String translationResult = translatorService.translate(src, dest, text);

                                if (translationResult.equals("-1")) {
                                    LOGGER.error("error on translation for sentence: " + message);
                                    messengerService.sendMessage("sorry i could not translate your sentence",
                                            messengerEntry.getMessaging()[0].getSender().getId());
                                } else {
                                    messengerService.sendMessage(translationResult,
                                            messengerEntry.getMessaging()[0].getSender().getId());
                                }
                            } else {
                                LOGGER.error("erroronous sentence: " + message);
                                messengerService.sendMessage("sorry i could not understand your sentence",
                                        messengerEntry.getMessaging()[0].getSender().getId());
                            }
                        }

                    }

                }
            }

        }

    }

    // for verification
    @RequestMapping(method = RequestMethod.GET, value = "/webhook")
    public String entryPoint(ServletRequest httpRequest) {
        return httpRequest.getParameter("hub.challenge");
    }
}
