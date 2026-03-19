package br.mackenzie.sistema_matriculas;

public class Aluno {
    private Integer id;
    private String nome;

    public Aluno() {}
    public Aluno(String nome) { 
        this.nome = nome; }

    
    public Integer getId() { 
        return id; }

    public void setId(Integer id) { 
        this.id = id; }

    public String getNome() { 
        return nome; }

    public void setNome(String nome) { 
        this.nome = nome; }
}