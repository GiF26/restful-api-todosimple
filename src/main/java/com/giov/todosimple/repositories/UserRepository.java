package com.giov.todosimple.repositories;

import com.giov.todosimple.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /* ESSA INTERFACE ESTA HERDANDO METODOS DA JPAREPOSITORY ONDE TRATA AS MANIPULACOES DAS TABELAS NO BANCO
    * ESSA CLASSE SERA CHAMADA E MANIPULADA PELO DIRETORIO DE "SERVICES"
    */
}
