/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import controladores.PeliculaJpaController;
import controladores.UsuarioJpaController;
import entidades.Pelicula;
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
//        u.setNombreUsuario("Joel Flores");
//        u.setContrasenaUsuario("Maria");
//        u.setTipoUsuario(true);
//        UsuarioJpaController ujc = new UsuarioJpaController();
          Pelicula p = new Pelicula();
          p.setPeliculaId(1);
          p.setPeliculaNombre("MonsterInc");
          p.setPeliculaDirector("Leonardo di caprio");
          p.setPeliculaDuracion(125);
          p.setPeliculaProductor("Warner");
          p.setPeliculaClasificacion("C");
          p.setPeliculaGenero("Terror");
        PeliculaJpaController pjc = new PeliculaJpaController();
        try {
//            ujc.create(u);
              pjc.create(p);
        } catch (Exception ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
