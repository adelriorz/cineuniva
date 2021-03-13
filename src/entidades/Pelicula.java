/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Armando Del Rio
 */
@Entity
@Table(name = "pelicula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pelicula.findAll", query = "SELECT p FROM Pelicula p")
    , @NamedQuery(name = "Pelicula.findByPeliculaId", query = "SELECT p FROM Pelicula p WHERE p.peliculaId = :peliculaId")
    , @NamedQuery(name = "Pelicula.findByPeliculaNombre", query = "SELECT p FROM Pelicula p WHERE p.peliculaNombre = :peliculaNombre")
    , @NamedQuery(name = "Pelicula.findByPeliculaDirector", query = "SELECT p FROM Pelicula p WHERE p.peliculaDirector = :peliculaDirector")
    , @NamedQuery(name = "Pelicula.findByPeliculaProductor", query = "SELECT p FROM Pelicula p WHERE p.peliculaProductor = :peliculaProductor")
    , @NamedQuery(name = "Pelicula.findByPeliculaClasificacion", query = "SELECT p FROM Pelicula p WHERE p.peliculaClasificacion = :peliculaClasificacion")
    , @NamedQuery(name = "Pelicula.findByPeliculaDuracion", query = "SELECT p FROM Pelicula p WHERE p.peliculaDuracion = :peliculaDuracion")
    , @NamedQuery(name = "Pelicula.findByPeliculaGenero", query = "SELECT p FROM Pelicula p WHERE p.peliculaGenero = :peliculaGenero")
    , @NamedQuery(name = "Pelicula.findByPeliculaEstatus", query = "SELECT p FROM Pelicula p WHERE p.peliculaEstatus = :peliculaEstatus")})
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "peliculaId")
    private Integer peliculaId;
    @Basic(optional = false)
    @Column(name = "peliculaNombre")
    private String peliculaNombre;
    @Basic(optional = false)
    @Column(name = "peliculaDirector")
    private String peliculaDirector;
    @Basic(optional = false)
    @Column(name = "peliculaProductor")
    private String peliculaProductor;
    @Basic(optional = false)
    @Column(name = "peliculaClasificacion")
    private String peliculaClasificacion;
    @Basic(optional = false)
    @Column(name = "peliculaDuracion")
    private int peliculaDuracion;
    @Basic(optional = false)
    @Column(name = "peliculaGenero")
    private String peliculaGenero;
    @Basic(optional = false)
    @Column(name = "peliculaEstatus")
    private boolean peliculaEstatus;

    public Pelicula() {
    }

    public Pelicula(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Pelicula(Integer peliculaId, String peliculaNombre, String peliculaDirector, String peliculaProductor, String peliculaClasificacion, int peliculaDuracion, String peliculaGenero, boolean peliculaEstatus) {
        this.peliculaId = peliculaId;
        this.peliculaNombre = peliculaNombre;
        this.peliculaDirector = peliculaDirector;
        this.peliculaProductor = peliculaProductor;
        this.peliculaClasificacion = peliculaClasificacion;
        this.peliculaDuracion = peliculaDuracion;
        this.peliculaGenero = peliculaGenero;
        this.peliculaEstatus = peliculaEstatus;
    }

    public Integer getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getPeliculaNombre() {
        return peliculaNombre;
    }

    public void setPeliculaNombre(String peliculaNombre) {
        this.peliculaNombre = peliculaNombre;
    }

    public String getPeliculaDirector() {
        return peliculaDirector;
    }

    public void setPeliculaDirector(String peliculaDirector) {
        this.peliculaDirector = peliculaDirector;
    }

    public String getPeliculaProductor() {
        return peliculaProductor;
    }

    public void setPeliculaProductor(String peliculaProductor) {
        this.peliculaProductor = peliculaProductor;
    }

    public String getPeliculaClasificacion() {
        return peliculaClasificacion;
    }

    public void setPeliculaClasificacion(String peliculaClasificacion) {
        this.peliculaClasificacion = peliculaClasificacion;
    }

    public int getPeliculaDuracion() {
        return peliculaDuracion;
    }

    public void setPeliculaDuracion(int peliculaDuracion) {
        this.peliculaDuracion = peliculaDuracion;
    }

    public String getPeliculaGenero() {
        return peliculaGenero;
    }

    public void setPeliculaGenero(String peliculaGenero) {
        this.peliculaGenero = peliculaGenero;
    }

    public boolean getPeliculaEstatus() {
        return peliculaEstatus;
    }

    public void setPeliculaEstatus(boolean peliculaEstatus) {
        this.peliculaEstatus = peliculaEstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peliculaId != null ? peliculaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pelicula)) {
            return false;
        }
        Pelicula other = (Pelicula) object;
        if ((this.peliculaId == null && other.peliculaId != null) || (this.peliculaId != null && !this.peliculaId.equals(other.peliculaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pelicula[ peliculaId=" + peliculaId + " ]";
    }
    
}
