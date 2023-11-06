package edu.criandoapi.criandoapi.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_enunciadoassercoes")
public class EnunciadoAssercoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean temRelacao;
    private String textoRelacao;
    private String posAssercoes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Assercao> assercoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTemRelacao() {
        return temRelacao;
    }

    public void setTemRelacao(boolean temRelacao) {
        this.temRelacao = temRelacao;
    }

    public String getTextoRelacao() {
        return textoRelacao;
    }

    public void setTextoRelacao(String textoRelacao) {
        this.textoRelacao = textoRelacao;
    }

    public String getPosAssercoes() {
        return posAssercoes;
    }

    public void setPosAssercoes(String posAssercoes) {
        this.posAssercoes = posAssercoes;
    }

    public List<Assercao> getAssercoes() {
        return assercoes;
    }

    public void setAssercoes(List<Assercao> assercoes) {
        this.assercoes = assercoes;
    }
}
