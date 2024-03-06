/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_1.dao;

import java.sql.*;
import br.com.projeto_1.dto.ClienteDTO;

/**
 *
 * @author gbiz0
 */
public class ClienteDAO {

    public ClienteDAO() {
    }

    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;

    public boolean inserirCliente(ClienteDTO clienteDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            pstmt = ConexaoDAO.con.prepareStatement("INSERT INTO cliente (nome_cli, logradouro_cli, numero_cli, bairro_cli, cidade_cli, estado_cli, cep_cli, cpf_cli, rg_cli)"
                    + "values (?,?,?,?,?,?,?,?,?);");
            pstmt.setString(1, clienteDTO.getNome_cli());
            pstmt.setString(2, clienteDTO.getLogradouro_cli());
            pstmt.setInt(3, clienteDTO.getNumero_cli());
            pstmt.setString(4, clienteDTO.getBairro_cli());
            pstmt.setString(5, clienteDTO.getCidade_cli());
            pstmt.setString(6, clienteDTO.getEstado_cli());
            pstmt.setString(7, clienteDTO.getCep_cli()); 
            pstmt.setString(8, clienteDTO.getCpf_cli());
            pstmt.setString(9, clienteDTO.getRg_cli());
            pstmt.execute();
            ConexaoDAO.con.commit();
            pstmt.close();
            return true;
        } catch (Exception e) {
            System.out.println("Problema ao abrir base de dados! " + e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){
        try {
            ConexaoDAO.ConnectDB();
            stmt =  ConexaoDAO.con.createStatement();
            String comando = "";
            
            switch (opcao){
                case 1: 
                    comando = "Select c.* "+
                              "from cliente c "+
                              "where nome_cli like '"+clienteDTO.getNome_cli()+"%' order by c.nome_cli";
                break;
                case 2: 
                    comando = "Select c.* "+
                              "from cliente c "+
                              "where c.id_cli = "+clienteDTO.getId_cli();
                break;
                case 3: 
                    comando = "Select c.id_cli, c.nome_cli"+
                              "from cliente c ";
                break;
            }
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
}//close class