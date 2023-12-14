package com.example.service;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.CustomExceptions.*;
import com.example.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

  private MessageRepository messageRepository;
  private AccountRepository accountRepository;

  public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
    this.messageRepository = messageRepository;
    this.accountRepository = accountRepository;
  }

  public Message createMessage(Message message){
    if(validMessage(message)){
      return messageRepository.save(message);
    }
    return null;
  }

  public List<Message> getAllMessages(){
    return (List<Message>) messageRepository.findAll();
  }

  public Message getMessageById(Integer message_id){
    return messageRepository.findById(message_id).orElse(null);
  }

  public void deleteMessage(Integer message_id){
    messageRepository.deleteById(message_id);
  }

  public void updateMessage(Message messageToUpdate, String updatedText){
    messageToUpdate.setMessage_text(updatedText);
    if(validMessage(messageToUpdate)){
      messageRepository.save(messageToUpdate);
    }
  }


  public boolean validMessage(Message message){
    if(message.getMessage_text().isEmpty()){
      throw new InputInvalidException("Message cannot be empty.");
    }else if(message.getMessage_text().length() > 255){
      throw new InputInvalidException("Message cannot exceed 255 Characters.class");
    }else if(accountRepository.findById(message.getPosted_by()).isEmpty()){
      throw new InputInvalidException("User Id invalid.");
    }else {
      return true;
    }
  }

}
