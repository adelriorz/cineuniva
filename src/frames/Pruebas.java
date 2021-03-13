/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import controladores.UsuarioJpaController;
import entidades.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.runtime.Log;

/**
 *
 * @author Armando Del Rio
 */
public class Pruebas {
    public static void main(String[] args) {
        Usuario u = new Usuario();
        //u.setId(2);
//        u.setNombre("Joel Flores");
//        u.setContrasena("maria");
//        u.setTipoUsuario(true);
//        UsuarioJpaController ujc = new UsuarioJpaController();
        try {
//            ujc.create(u);
        } catch (Exception ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
