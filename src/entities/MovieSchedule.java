/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Armando Del Rio
 */
@Entity
@Table(name = "movie_schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovieSchedule.findAll", query = "SELECT m FROM MovieSchedule m")
    , @NamedQuery(name = "MovieSchedule.findByMovieIdfk", query = "SELECT m FROM MovieSchedule m WHERE m.movieSchedulePK.movieIdfk = :movieIdfk")
    , @NamedQuery(name = "MovieSchedule.findByScheduleIdfk", query = "SELECT m FROM MovieSchedule m WHERE m.movieSchedulePK.scheduleIdfk = :scheduleIdfk")
    , @NamedQuery(name = "MovieSchedule.findByRoomIdfk", query = "SELECT m FROM MovieSchedule m WHERE m.movieSchedulePK.roomIdfk = :roomIdfk")})
public class MovieSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MovieSchedulePK movieSchedulePK;
    @JoinColumn(name = "movieId_fk", referencedColumnName = "movieId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movie movie;
    @JoinColumns({
        @JoinColumn(name = "scheduleId_fk", referencedColumnName = "scheduleId", insertable = false, updatable = false)
        , @JoinColumn(name = "roomId_fk", referencedColumnName = "roomId", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Schedule schedule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieSchedule")
    private List<MovieLocation> movieLocationList;

    public MovieSchedule() {
    }

    public MovieSchedule(MovieSchedulePK movieSchedulePK) {
        this.movieSchedulePK = movieSchedulePK;
    }

    public MovieSchedule(int movieIdfk, int scheduleIdfk, int roomIdfk) {
        this.movieSchedulePK = new MovieSchedulePK(movieIdfk, scheduleIdfk, roomIdfk);
    }

    public MovieSchedulePK getMovieSchedulePK() {
        return movieSchedulePK;
    }

    public void setMovieSchedulePK(MovieSchedulePK movieSchedulePK) {
        this.movieSchedulePK = movieSchedulePK;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @XmlTransient
    public List<MovieLocation> getMovieLocationList() {
        return movieLocationList;
    }

    public void setMovieLocationList(List<MovieLocation> movieLocationList) {
        this.movieLocationList = movieLocationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieSchedulePK != null ? movieSchedulePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieSchedule)) {
            return false;
        }
        MovieSchedule other = (MovieSchedule) object;
        if ((this.movieSchedulePK == null && other.movieSchedulePK != null) || (this.movieSchedulePK != null && !this.movieSchedulePK.equals(other.movieSchedulePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieSchedule[ movieSchedulePK=" + movieSchedulePK + " ]";
    }
    
}
