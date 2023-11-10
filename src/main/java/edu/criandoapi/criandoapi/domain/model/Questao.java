package edu.criandoapi.criandoapi.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "tb_questao")
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroDaQuestao;
    private boolean anulada;
    private boolean temAssercoes;
    private String enunciado;
    private String opcaoCorreta;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TextoDeApoio> textosDeApoio;

    @OneToOne(cascade = CascadeType.ALL)
    private Respostas respostas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private EnunciadoAssercoes enunciadoAssercoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public boolean isTemAssercoes() {
        return temAssercoes;
    }

    public void setTemAssercoes(boolean temAssercoes) {
        this.temAssercoes = temAssercoes;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getNumeroDaQuestao() {
        return numeroDaQuestao;
    }

    public void setNumeroDaQuestao(int numeroDaQuestao) {
        this.numeroDaQuestao = numeroDaQuestao;
    }

    public String getOpcaoCorreta() {
        return opcaoCorreta;
    }

    public void setOpcaoCorreta(String opcaoCorreta) {
        this.opcaoCorreta = opcaoCorreta;
    }

    public List<TextoDeApoio> getTextosDeApoio() {
        return textosDeApoio;
    }

    public void setTextosDeApoio(List<TextoDeApoio> textosDeApoio) {
        this.textosDeApoio = textosDeApoio;
    }

    public Respostas getRespostas() {
        return respostas;
    }

    public void setRespostas(Respostas respostas) {
        this.respostas = respostas;
    }

    public EnunciadoAssercoes getEnunciadoAssercoes() {
        return enunciadoAssercoes;
    }

    public void setEnunciadoAssercoes(EnunciadoAssercoes enunciadoAssercoes) {
        this.enunciadoAssercoes = enunciadoAssercoes;
    }

}
