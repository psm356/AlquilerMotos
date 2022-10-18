package com.mintic_usa.AlquilerMotos.Service;

import com.mintic_usa.AlquilerMotos.Model.Admin;
import com.mintic_usa.AlquilerMotos.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAll(){
        return adminRepository.getAll();
    }
    public Optional<Admin> getAdmin(int id){
        return adminRepository.getAdmin(id);
    }
    public Admin save(Admin admin){
        if(admin.getIdAdmin() == null){
            return adminRepository.save(admin);
        }else{
            Optional<Admin> aux = adminRepository.getAdmin(admin.getIdAdmin());
            if(aux.isPresent()){
                return admin;
            }else{
                return adminRepository.save(admin);
            }
        }
    }
    public Admin update(Admin admin){
        if(admin.getIdAdmin() != null){
            Optional<Admin> a = adminRepository.getAdmin(admin.getIdAdmin());
            if(a.isPresent()){
                if(admin.getEmail() != null){
                    a.get().setEmail(admin.getEmail());
                }
                if(admin.getName() != null){
                    a.get().setName(admin.getName());
                }
                if(admin.getPassword() != null){
                    a.get().setPassword(admin.getPassword());
                }
                adminRepository.save(a.get());
                return a.get();
            }else{
                return admin;
            }
        }else{
            return admin;
        }
    }

    public boolean delete(int id){
        boolean flag = false;
        Optional<Admin> a = adminRepository.getAdmin(id);
        if(a.isPresent()){
            adminRepository.delete(a.get());
        }
        return flag;
    }
}
