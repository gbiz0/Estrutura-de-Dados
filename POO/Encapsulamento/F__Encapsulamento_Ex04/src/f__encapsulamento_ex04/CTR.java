/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package f__encapsulamento_ex04;

/**
 *
 * @author gbiz0
 */
public class CTR {
    DAO dao = new DAO();
    
    public String imprimir(DTO dto){
        return dao.imprimir(dto);
    }
}
