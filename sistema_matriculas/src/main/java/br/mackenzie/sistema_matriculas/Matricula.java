package br.mackenzie.sistema_matriculas;

public class Matricula {
    private Integer id;
    private Integer alunoId;
    private String semestre;

    public Matricula() {}
    public Matricula(Integer alunoId, String semestre) {
        this.alunoId = alunoId;
        this.semestre = semestre;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getAlunoId() { return alunoId; }
    public void setAlunoId(Integer alunoId) { this.alunoId = alunoId; }
    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
}