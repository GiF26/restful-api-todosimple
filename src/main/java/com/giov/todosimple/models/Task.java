package com.giov.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne //ANNOTATION QUE DEFINE QUE MUITAS TAREFAS SAO DE UM USUARIO NA TABELA "TASK"
    @JoinColumn(name = "user_id", nullable = false, updatable = false)//DEFINE QUE ESSE CAMPO E UMA CHAVE ESTRANGEIRA
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task() {
    }

    public Task(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this){return true;}
        if (o == null){return false;}
        if (!(o instanceof Task)){return false;}

        Task other = (Task) o;

        if(this.id == null && other.id != null){
            return false;
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return Objects.equals(this.id, other.id) &&
                Objects.equals(this.user, other.user) &&
                Objects.equals(this.description, other.description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
