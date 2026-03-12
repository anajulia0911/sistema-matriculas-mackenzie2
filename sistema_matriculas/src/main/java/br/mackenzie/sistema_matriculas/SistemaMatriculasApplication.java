package br.mackenzie.sistema_matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.*;

@SpringBootApplication
public class SistemaMatriculasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaMatriculasApplication.class, args);

        
        String url = "jdbc:postgresql://aws-0-us-west-2.pooler.supabase.com:6543/postgres"
                   + "?user=postgres.gtrxxhynlnobvwzhqlpn"
                   + "&password=mackenziebdsistema"
                   + "&sslmode=disable"
                   + "&prepareThreshold=0";

        System.out.println("\n--- TENTANDO CONEXÃO DIRETA MACKENZIE ---");

        try (Connection con = DriverManager.getConnection(url)) {
            System.out.println("SUCESSO: CONECTADO AO SUPABASE!");

            Statement stmt = con.createStatement();
            
            
            stmt.execute("CREATE TABLE IF NOT EXISTS aluno (id SERIAL PRIMARY KEY, nome TEXT)");
            
           
            stmt.executeUpdate("DELETE FROM aluno");
            stmt.executeUpdate("INSERT INTO aluno (nome) VALUES ('Ana Julia Mackenzie')");
            
           
            ResultSet rs = stmt.executeQuery("SELECT nome FROM aluno");
            if (rs.next()) {
                System.out.println("DADO NO BANCO: " + rs.getString("nome"));
            }
            System.out.println("LOG: TUDO PRONTO!");

        } catch (SQLException e) {
            System.err.println("\nERRO DE CONEXÃO:");
            System.err.println("Mensagem: " + e.getMessage());
            System.err.println("DICA: Verifique se o seu Projeto ID no Supabase mudou.");
        }
    }
}