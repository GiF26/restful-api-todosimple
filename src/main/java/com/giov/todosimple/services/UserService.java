package com.giov.todosimple.services;

import com.giov.todosimple.models.User;
import com.giov.todosimple.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // ANNOTATION RESPONSAVEL POR INDICAR QUE E UM SERVICE (SOBE JUNTO COM O SERVIDOR)
// USADO PARA SE COMUNICAR COM OS REPOSITORIES )
public class UserService {
    @Autowired ///permite que o Spring resolva e injete automaticamente as dependências necessárias em seus componentes.
    private UserRepository userRepository;

    public User findById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found ! \nId: " + id +
                                                            "\nUser: " + user +
                                                            "\nType:" + User.class.getSimpleName()));
    }

    @Transactional // ANNOTATION RESPONSAVEL POR CONTROLAR A ALTERACAO/INCLUSAO DE REGISTROS NO BANCO
    //PARECIDO COM ROLLBACK
    public User create(User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error deleting user because already relational entity's! \nId: " + id);
        }
    }
}
