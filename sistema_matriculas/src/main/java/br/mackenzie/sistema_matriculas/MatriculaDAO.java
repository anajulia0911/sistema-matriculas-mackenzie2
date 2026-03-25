package br.mackenzie.sistema_matriculas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {
    private String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres"
                       + "?user=postgres.ypoqwjllfwshtcoswtqg"
                       + "&password=mackenziebdsistema" 
                       + "&sslmode=disable"
                       + "&prepareThreshold=0";

    public void salvar(Matricula matricula) {
        String createSql = "CREATE TABLE IF NOT EXISTS matricula (id SERIAL PRIMARY KEY, " +
                           "aluno_id INTEGER REFERENCES aluno(id), semestre TEXT)";
        String insertSql = "INSERT INTO matricula (aluno_id, semestre) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(url)) {
            Statement st = con.createStatement();
            st.execute(createSql);

            try (PreparedStatement stmt = con.prepareStatement(insertSql)) {
                stmt.setInt(1, matricula.getAlunoId());
                stmt.setString(2, matricula.getSemestre());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Erro no DAO ao salvar matricula: " + e.getMessage());
        }
    }

    public List<Matricula> listarPorAluno(int alunoId) {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula WHERE aluno_id = ?";
        try (Connection con = DriverManager.getConnection(url);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Matricula m = new Matricula();
                m.setId(rs.getInt("id"));
                m.setAlunoId(rs.getInt("aluno_id"));
                m.setSemestre(rs.getString("semestre"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar matriculas: " + e.getMessage());
        }
        return lista;
    }
}