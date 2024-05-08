package com.DEVLooping.userAPI.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DEVLooping.userAPI.service.EncryptService;

@RestController
@RequestMapping("/api")

public class EncryptorRestController {

    EncryptService encryptService = new EncryptService();

    @GetMapping("/encrypt/{text}")
    public String encrypt(@PathVariable String text) {
        String encryptedText = encryptService.encrypt(text);

        return encryptedText;
    }

    @GetMapping("/decrypt/{text}")
    public String decrypt(@PathVariable String text) {
        String decryptedText = encryptService.decrypt(text);
        
        return decryptedText;
    }    
}
