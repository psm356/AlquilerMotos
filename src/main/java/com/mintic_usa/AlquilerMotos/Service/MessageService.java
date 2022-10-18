package com.mintic_usa.AlquilerMotos.Service;

import com.mintic_usa.AlquilerMotos.Model.Message;
import com.mintic_usa.AlquilerMotos.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }
    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }
    public Message save(Message message){
        if(message.getIdMessage() == null){
            return messageRepository.save(message);
        }else{
            Optional<Message> aux = messageRepository.getMessage(message.getIdMessage());
            if(aux.isPresent()){
                return message;
            }else{
                return messageRepository.save(message);
            }
        }
    }
    public Message update(Message message){
        if(message.getIdMessage() != null){
            Optional<Message> m = messageRepository.getMessage(message.getIdMessage());
            if(m.isPresent()){
                if(message.getMessageText() != null){
                    m.get().setMessageText(message.getMessageText());
                }
                messageRepository.save(m.get());
                return m.get();
            }else{
                return message;
            }
        }else{
            return message;
        }
    }

    public boolean delete(int id){
        boolean flag = false;
        Optional<Message> m = messageRepository.getMessage(id);
        if(m.isPresent()){
            messageRepository.delete(m.get());
        }
        return flag;
    }
}
