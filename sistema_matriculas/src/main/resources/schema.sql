CREATE TABLE IF NOT EXISTS aluno (
    aluno_id INT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS matricula (
    matricula_id INT PRIMARY KEY,
    semestre VARCHAR(10) NOT NULL,
    aluno_id INT,
    CONSTRAINT fk_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(aluno_id)
);