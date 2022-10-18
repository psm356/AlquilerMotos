package com.mintic_usa.AlquilerMotos.Service;

import com.mintic_usa.AlquilerMotos.Model.Client;
import com.mintic_usa.AlquilerMotos.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    public Optional<Client> getClient(int id){
        return clientRepository.getClient(id);
    }
    public Client save(Client client){
        if(client.getIdClient() == null){
            return clientRepository.save(client);
        }else{
            Optional<Client> aux = clientRepository.getClient(client.getIdClient());
            if(aux.isPresent()){
                return client;
            }else{
                return clientRepository.save(client);
            }
        }
    }
    public Client update(Client client){
        if(client.getIdClient() != null){
            Optional<Client> e = clientRepository.getClient(client.getIdClient());
            if(e.isPresent()){
                if(client.getEmail() != null){
                    e.get().setEmail(client.getEmail());
                }
                if(client.getPassword() != null){
                    e.get().setPassword(client.getPassword());
                }
                if(client.getName() != null){
                    e.get().setName(client.getName());
                }
                if(client.getAge() != null){
                    e.get().setAge(client.getAge());
                }
                if(client.getMessages() != null){
                    e.get().setMessages(client.getMessages());
                }
                if(client.getReservations() != null){
                    e.get().setReservations(client.getReservations());
                }
                clientRepository.save(e.get());
                return e.get();
            }else{
                return client;
            }
        }else{
            return client;
        }
    }

    public boolean delete(int id){
        boolean flag = false;
        Optional<Client> e = clientRepository.getClient(id);
        if(e.isPresent()){
            clientRepository.delete(e.get());
        }
        return flag;
    }
}
