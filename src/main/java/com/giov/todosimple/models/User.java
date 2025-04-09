package com.giov.todosimple.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = User.TABLE_NAME) // ANOTATION RESPONSAVEL POR CRIAR TABELA NO BANCO E DEFINIR O NOME DELA
@Entity // ANNOTARION RESPONSAVEL POR DEFINIR QUE ESSA CLASSE É UMA DAS NOSSAS CLASSES DO BANCO DE DADOS
public class User {
    //INTERFACES RESPONSAVEIS POR DEFINIR VALIDAÇÕES NA CRIAÇÃO E EDIÇÃO DE REGISTROS
    public interface CreateUser {}
    public interface UpdateUser {}
    //VARIAVEL CONSTANTE QUE DEFINE O NOME DA TABELA QUE IRA SER CRIADA
    public static final String TABLE_NAME = "user";

    @Id//ANNOTATION QUE DEFINE CHAVE DA TABELA (CODIGO UNICO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ANNOTATION QUE GERA CHAVE (CODIGO UNICO) AUTOMATICAMENTE
                                                       //A PROPRIEDADE IDENTITY ????
    @Column(name = "id", unique = true) // ANNOTATION RESPONSAVEL POR CRIAR COLUNAS NA TABELA NO BANCO DE DADOS
    private Long id; //ATRIBUTO DA CLASSE

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotBlank(groups = CreateUser.class)//ANNOTATION QUE DEFINE QUE O VALOR DA COLUNA NAO PODERA SER NULO E NEM VAZIO
    @Size(groups = CreateUser.class, min = 2, max = 100)//ANNOTATION SIZE DEFINE A QUANTIDADE MINIMA E MAXIMA DE POSICOES QUE O VALOR DA COLUNA TERA
                                                        // * NO CASO DESSA LINHA ESTÁ DEFINIDO ATÉ O PERFIL DE VALIDAÇÃO ÁRA CRIAÇÃO DO VALOR
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //ANNOTATION NAO RETORNAR SENHA NO JSON
    @Column(name = "password", length = 100, nullable = false)
    @NotBlank(groups = CreateUser.class)
    @Size(groups = CreateUser.class,min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "user") //ANNOTATION QUE DEFINE QUE UM USUARIO PODE TER MUITAS TASKS (CRIACAO DE MODELO RELACIONAL NO BANCO)
    private List<Task> tasks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this){return true;}
        if (o == null){return false;}
        if (!(o instanceof User)){return false;}

        User other = (User) o;

        if(this.id == null && other.id != null){
                return false;
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return Objects.equals(this.id, other.id) &&
                    Objects.equals(this.username, other.username) &&
                    Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
