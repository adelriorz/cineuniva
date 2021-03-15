/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Armando Del Rio
 */
@Entity
@Table(name = "movie_location")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieLocation.findAll", query = "SELECT m FROM MovieLocation m")
    , @NamedQuery(name = "MovieLocation.findByMunicipalityIdfk", query = "SELECT m FROM MovieLocation m WHERE m.movieLocationPK.municipalityIdfk = :municipalityIdfk")
    , @NamedQuery(name = "MovieLocation.findByStateIdfk", query = "SELECT m FROM MovieLocation m WHERE m.movieLocationPK.stateIdfk = :stateIdfk")
    , @NamedQuery(name = "MovieLocation.findByMovieIdfk", query = "SELECT m FROM MovieLocation m WHERE m.movieLocationPK.movieIdfk = :movieIdfk")
    , @NamedQuery(name = "MovieLocation.findByScheduleIdfk", query = "SELECT m FROM MovieLocation m WHERE m.movieLocationPK.scheduleIdfk = :scheduleIdfk")
    , @NamedQuery(name = "MovieLocation.findByRoomIdfk", query = "SELECT m FROM MovieLocation m WHERE m.movieLocationPK.roomIdfk = :roomIdfk")})
public class MovieLocation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovieLocationPK movieLocationPK;
    @JoinTable(name = "user_select_movie", joinColumns = {
        @JoinColumn(name = "municipalityId_fk", referencedColumnName = "municipalityId_fk")
        , @JoinColumn(name = "stateId_fk", referencedColumnName = "stateId_fk")
        , @JoinColumn(name = "movieId_fk", referencedColumnName = "movieId_fk")
        , @JoinColumn(name = "scheduleId_fk", referencedColumnName = "scheduleId_fk")
        , @JoinColumn(name = "roomId_fk", referencedColumnName = "roomId_fk")}, inverseJoinColumns = {
        @JoinColumn(name = "userId_fk", referencedColumnName = "userId")})
    @ManyToMany
    private List<User> userList;
    @JoinColumns({
        @JoinColumn(name = "movieId_fk", referencedColumnName = "movieId_fk", insertable = false, updatable = false)
        , @JoinColumn(name = "scheduleId_fk", referencedColumnName = "scheduleId_fk", insertable = false, updatable = false)
        , @JoinColumn(name = "roomId_fk", referencedColumnName = "roomId_fk", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private MovieSchedule movieSchedule;
    @JoinColumns({
        @JoinColumn(name = "municipalityId_fk", referencedColumnName = "municipalityId", insertable = false, updatable = false)
        , @JoinColumn(name = "stateId_fk", referencedColumnName = "stateId", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Municipality municipality;

    public MovieLocation() {
    }

    public MovieLocation(MovieLocationPK movieLocationPK) {
        this.movieLocationPK = movieLocationPK;
    }

    public MovieLocation(int municipalityIdfk, int stateIdfk, int movieIdfk, int scheduleIdfk, int roomIdfk) {
        this.movieLocationPK = new MovieLocationPK(municipalityIdfk, stateIdfk, movieIdfk, scheduleIdfk, roomIdfk);
    }

    public MovieLocationPK getMovieLocationPK() {
        return movieLocationPK;
    }

    public void setMovieLocationPK(MovieLocationPK movieLocationPK) {
        this.movieLocationPK = movieLocationPK;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public MovieSchedule getMovieSchedule() {
        return movieSchedule;
    }

    public void setMovieSchedule(MovieSchedule movieSchedule) {
        this.movieSchedule = movieSchedule;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieLocationPK != null ? movieLocationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieLocation)) {
            return false;
        }
        MovieLocation other = (MovieLocation) object;
        if ((this.movieLocationPK == null && other.movieLocationPK != null) || (this.movieLocationPK != null && !this.movieLocationPK.equals(other.movieLocationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieLocation[ movieLocationPK=" + movieLocationPK + " ]";
    }
    
}
