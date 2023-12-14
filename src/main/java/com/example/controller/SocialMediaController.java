package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.*;
import com.example.entity.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

  private AccountService accountService;
  private MessageService messageService;

  public SocialMediaController(AccountService accountService, MessageService messageService){
    this.accountService = accountService;
    this.messageService = messageService;
  }

  @PostMapping("/login")
  public ResponseEntity<Account> login(@RequestBody Account account){
    Account loggedInAccount = accountService.login(account);
    return ResponseEntity.ok(loggedInAccount);
  }

  @PostMapping("/register")
  public ResponseEntity<Account> registerAccount(@RequestBody Account account){
    Account registeredAccount = accountService.registerAccount(account);
    return ResponseEntity.ok(registeredAccount);
  }

  @PostMapping("/messages")
  public ResponseEntity<Message> createMessage(@RequestBody Message message){
    Message createdMessage = messageService.createMessage(message);
    return ResponseEntity.ok(createdMessage);
  }

  @GetMapping("/messages")
  public ResponseEntity<List<Message>> getAllMessages(){
    List<Message> messages = messageService.getAllMessages();
    return ResponseEntity.ok(messages);
  }  

  @GetMapping("/messages/{message_id}")
  public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id){
    Message message = messageService.getMessageById(message_id);
    return ResponseEntity.ok(message);
  }

  @DeleteMapping("/messages/{message_id}")
  public ResponseEntity<?> deleteMessage(@PathVariable Integer message_id){
    Message messageToDelete = messageService.getMessageById(message_id);
    if(messageToDelete != null){
      messageService.deleteMessage(messageToDelete.getMessage_id());
      return ResponseEntity.ok(1);
    } else {
      return ResponseEntity.ok().build();
    }
  }

}
