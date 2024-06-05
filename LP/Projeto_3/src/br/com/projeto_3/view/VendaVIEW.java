/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.projeto_3.view;
import br.com.projeto_3.ctr.VendaCTR;
import br.com.projeto_3.dto.VendaDTO;
import br.com.projeto_3.dto.ProdutoDTO;
import br.com.projeto_3.ctr.ProdutoCTR;
import br.com.projeto_3.dto.ClienteDTO;
import br.com.projeto_3.ctr.ClienteCTR;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

import java.util.Date;


/**
 *
 * @author gbiz0
 */
public class VendaVIEW extends javax.swing.JInternalFrame {

    VendaCTR vendaCTR = new VendaCTR();
    VendaDTO vendaDTO = new VendaDTO();
    ProdutoCTR produtoCTR = new ProdutoCTR();
    ProdutoDTO produtoDTO = new ProdutoDTO();
    ClienteCTR clienteCTR = new ClienteCTR();
    ClienteDTO clienteDTO = new ClienteDTO();
    
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_cli;
    DefaultTableModel modelo_jtl_consultar_pro;
    DefaultTableModel modelo_jtl_consultar_pro_selecionado;
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    public void gravar() {
        try {
            vendaDTO.setDat_vend(new Date());
            vendaDTO.setVal_vend(Double.parseDouble(TotalVenda.getText()));
            clienteDTO.setId_cli(Integer.parseInt(String.valueOf(
                    jtl_colsultar_cli.getValueAt(jtl_colsultar_cli.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null, 
                    vendaCTR.inserirVenda(vendaDTO, clienteDTO, jtl_colsultar_pro_selecionado));
        } catch (Exception e) {
            System.out.println("Erro ao gravar " + e.getMessage());
        }
    }
    
    public void preencheTabelaCliente(String nome_cli) {
        try {
            modelo_jtl_consultar_cli.setNumRows(0);
            clienteDTO.setNome_cli(nome_cli);

            rs = clienteCTR.consultarCliente(clienteDTO, 1);

            while (rs.next()) {
                modelo_jtl_consultar_cli.addRow(new Object[]{
                    rs.getString("id_cli"),
                    rs.getString("nome_cli"),});
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: " + erTab);
        } finally {
            clienteCTR.CloseDB();
        }
    }
    
    public void preencheTabelaProduto(String nome_prod) {
        try {
            modelo_jtl_consultar_pro.setNumRows(0);
            produtoDTO.setNome_prod(nome_prod);

            rs = produtoCTR.consultarProduto(produtoDTO, 1);

            while (rs.next()) {
                modelo_jtl_consultar_pro.addRow(new Object[]{
                    rs.getString("id_prod"),
                    rs.getString("nome_prod"),
                    rs.getDouble("p_venda_prod"),});
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: "+erTab);
        } finally {
            produtoCTR.CloseDB();
        }
    }
    
    private void calculaTotalVenda(){
        try {
            double total = 0;
            for (int cont = 0; cont < jtl_colsultar_pro_selecionado.getRowCount(); cont++) {
                total += (Double.parseDouble(String.valueOf(
                        jtl_colsultar_pro_selecionado.getValueAt(cont, 2)))*
                        Integer.parseInt(String.valueOf(
                                jtl_colsultar_pro_selecionado.getValueAt(cont, 3))));
            }
            TotalVenda.setText(String.valueOf(total));
            
        } catch (Exception erTab) {
            System.out.println("Erro SQL: "+erTab);
        }
    }
    
    private void adicionaProdutoSelecionado(int id_prod, String nome_prod, double p_venda_prod){
        try {
            modelo_jtl_consultar_pro_selecionado.addRow(new Object[]{
                id_prod,
                nome_prod,
                p_venda_prod
            });
        } catch (Exception erTab) {
            System.out.println("Erro SQL: "+erTab);
        }
    }

    private void removeProdutoSelecionado(int linha_selecionada){
        try {
            if (linha_selecionada >= 0){
                 modelo_jtl_consultar_pro_selecionado.removeRow(linha_selecionada);
                 calculaTotalVenda();
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: "+erTab);
        }
    }
    
    public VendaVIEW() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pesquisa_nome_cli = new javax.swing.JTextField();
        btnPesquisarCli = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_colsultar_cli = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        TotalVenda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pesquisa_nome_pro = new javax.swing.JTextField();
        btnPesquisarPro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_colsultar_pro_selecionado = new javax.swing.JTable();
        BtnProAdd = new javax.swing.JButton();
        btnProRem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_colsultar_pro = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel1.setText("Cliente:");

        pesquisa_nome_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_cliActionPerformed(evt);
            }
        });

        btnPesquisarCli.setText("OK");
        btnPesquisarCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarCliActionPerformed(evt);
            }
        });

        jtl_colsultar_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ));
        jScrollPane1.setViewportView(jtl_colsultar_cli);

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 0, 36)); // NOI18N
        jLabel2.setText("Total Venda:");

        TotalVenda.setFont(new java.awt.Font("Liberation Sans", 0, 36)); // NOI18N
        TotalVenda.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_nome_cli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarCli))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TotalVenda)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pesquisa_nome_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TotalVenda))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        jLabel4.setText("Descrição:");

        pesquisa_nome_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_proActionPerformed(evt);
            }
        });

        btnPesquisarPro.setText("OK");
        btnPesquisarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarProActionPerformed(evt);
            }
        });

        jtl_colsultar_pro_selecionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Valor", "QTDE"
            }
        ));
        jScrollPane2.setViewportView(jtl_colsultar_pro_selecionado);

        BtnProAdd.setText("ADD");
        BtnProAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProAddActionPerformed(evt);
            }
        });

        btnProRem.setText("DELETE");

        jtl_colsultar_pro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Valor"
            }
        ));
        jScrollPane3.setViewportView(jtl_colsultar_pro);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_nome_pro, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarPro)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnProAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnProRem)
                .addGap(165, 165, 165))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pesquisa_nome_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarPro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnProAdd)
                    .addComponent(btnProRem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(201, Short.MAX_VALUE)))
        );

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        jLabel3.setText("Vendas");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/novo.png"))); // NOI18N

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/salvar.png"))); // NOI18N

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/cancelar.png"))); // NOI18N

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_3/view/imagens/sair.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(32, 32, 32))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair)
                .addGap(426, 426, 426))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnSair))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisa_nome_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_cliActionPerformed

    private void btnPesquisarCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarCliActionPerformed

    private void pesquisa_nome_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_proActionPerformed

    private void btnPesquisarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarProActionPerformed

    private void BtnProAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnProAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnProAdd;
    private javax.swing.JLabel TotalVenda;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarCli;
    private javax.swing.JButton btnPesquisarPro;
    private javax.swing.JButton btnProRem;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jtl_colsultar_cli;
    private javax.swing.JTable jtl_colsultar_pro;
    private javax.swing.JTable jtl_colsultar_pro_selecionado;
    private javax.swing.JTextField pesquisa_nome_cli;
    private javax.swing.JTextField pesquisa_nome_pro;
    // End of variables declaration//GEN-END:variables
}
