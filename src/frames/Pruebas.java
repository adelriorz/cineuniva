/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import controladores.UsuariosJpaController;
import entidades.Usuarios;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.runtime.Log;

/**
 *
 * @author Armando Del Rio
 */
public class Pruebas {
    public static void main(String[] args) {
        Usuarios u = new Usuarios();
        //u.setId(2);
        u.setNombre("Joel Flores");
        u.setContrasena("maria");
        u.setTipoUsuario(true);
        UsuariosJpaController ujc = new UsuariosJpaController();
        try {
            ujc.create(u);
        } catch (Exception ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
