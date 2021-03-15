/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Armando Del Rio
 */
@Embeddable
public class MovieSchedulePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "movieId_fk")
    private int movieIdfk;
    @Basic(optional = false)
    @Column(name = "scheduleId_fk")
    private int scheduleIdfk;
    @Basic(optional = false)
    @Column(name = "roomId_fk")
    private int roomIdfk;

    public MovieSchedulePK() {
    }

    public MovieSchedulePK(int movieIdfk, int scheduleIdfk, int roomIdfk) {
        this.movieIdfk = movieIdfk;
        this.scheduleIdfk = scheduleIdfk;
        this.roomIdfk = roomIdfk;
    }

    public int getMovieIdfk() {
        return movieIdfk;
    }

    public void setMovieIdfk(int movieIdfk) {
        this.movieIdfk = movieIdfk;
    }

    public int getScheduleIdfk() {
        return scheduleIdfk;
    }

    public void setScheduleIdfk(int scheduleIdfk) {
        this.scheduleIdfk = scheduleIdfk;
    }

    public int getRoomIdfk() {
        return roomIdfk;
    }

    public void setRoomIdfk(int roomIdfk) {
        this.roomIdfk = roomIdfk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) movieIdfk;
        hash += (int) scheduleIdfk;
        hash += (int) roomIdfk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovieSchedulePK)) {
            return false;
        }
        MovieSchedulePK other = (MovieSchedulePK) object;
        if (this.movieIdfk != other.movieIdfk) {
            return false;
        }
        if (this.scheduleIdfk != other.scheduleIdfk) {
            return false;
        }
        if (this.roomIdfk != other.roomIdfk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MovieSchedulePK[ movieIdfk=" + movieIdfk + ", scheduleIdfk=" + scheduleIdfk + ", roomIdfk=" + roomIdfk + " ]";
    }
    
}
