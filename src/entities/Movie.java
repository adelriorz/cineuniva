/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Armando Del Rio
 */
@Entity
@Table(name = "movie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m")
    , @NamedQuery(name = "Movie.findByMovieId", query = "SELECT m FROM Movie m WHERE m.movieId = :movieId")
    , @NamedQuery(name = "Movie.findByMovieName", query = "SELECT m FROM Movie m WHERE m.movieName = :movieName")
    , @NamedQuery(name = "Movie.findByMovieDirector", query = "SELECT m FROM Movie m WHERE m.movieDirector = :movieDirector")
    , @NamedQuery(name = "Movie.findByMovieProducer", query = "SELECT m FROM Movie m WHERE m.movieProducer = :movieProducer")
    , @NamedQuery(name = "Movie.findByMovieClassification", query = "SELECT m FROM Movie m WHERE m.movieClassification = :movieClassification")
    , @NamedQuery(name = "Movie.findByMovieDuration", query = "SELECT m FROM Movie m WHERE m.movieDuration = :movieDuration")
    , @NamedQuery(name = "Movie.findByMovieStatus", query = "SELECT m FROM Movie m WHERE m.movieStatus = :movieStatus")
    , @NamedQuery(name = "Movie.findByMovieCreatedAt", query = "SELECT m FROM Movie m WHERE m.movieCreatedAt = :movieCreatedAt")
    , @NamedQuery(name = "Movie.findByMovieUpdatedAt", query = "SELECT m FROM Movie m WHERE m.movieUpdatedAt = :movieUpdatedAt")})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movieId")
    private Integer movieId;
    @Basic(optional = false)
    @Column(name = "movieName")
    private String movieName;
    @Basic(optional = false)
    @Column(name = "movieDirector")
    private String movieDirector;
    @Basic(optional = false)
    @Column(name = "movieProducer")
    private String movieProducer;
    @Basic(optional = false)
    @Column(name = "movieClassification")
    private String movieClassification;
    @Basic(optional = false)
    @Column(name = "movieDuration")
    private int movieDuration;
    @Basic(optional = false)
    @Column(name = "movieStatus")
    private short movieStatus;
    @Basic(optional = false)
    @Column(name = "movieCreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date movieCreatedAt;
    @Basic(optional = false)
    @Column(name = "movieUpdatedAt")
    private String movieUpdatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    private List<Assistance> assistanceList;

    public Movie() {
    }

    public Movie(Integer movieId) {
        this.movieId = movieId;
    }

    public Movie(Integer movieId, String movieName, String movieDirector, String movieProducer, String movieClassification, int movieDuration, short movieStatus, Date movieCreatedAt, String movieUpdatedAt) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDirector = movieDirector;
        this.movieProducer = movieProducer;
        this.movieClassification = movieClassification;
        this.movieDuration = movieDuration;
        this.movieStatus = movieStatus;
        this.movieCreatedAt = movieCreatedAt;
        this.movieUpdatedAt = movieUpdatedAt;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieProducer() {
        return movieProducer;
    }

    public void setMovieProducer(String movieProducer) {
        this.movieProducer = movieProducer;
    }

    public String getMovieClassification() {
        return movieClassification;
    }

    public void setMovieClassification(String movieClassification) {
        this.movieClassification = movieClassification;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public short getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(short movieStatus) {
        this.movieStatus = movieStatus;
    }

    public Date getMovieCreatedAt() {
        return movieCreatedAt;
    }

    public void setMovieCreatedAt(Date movieCreatedAt) {
        this.movieCreatedAt = movieCreatedAt;
    }

    public String getMovieUpdatedAt() {
        return movieUpdatedAt;
    }

    public void setMovieUpdatedAt(String movieUpdatedAt) {
        this.movieUpdatedAt = movieUpdatedAt;
    }

    @XmlTransient
    public List<Assistance> getAssistanceList() {
        return assistanceList;
    }

    public void setAssistanceList(List<Assistance> assistanceList) {
        this.assistanceList = assistanceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movieId != null ? movieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.movieId == null && other.movieId != null) || (this.movieId != null && !this.movieId.equals(other.movieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Movie[ movieId=" + movieId + " ]";
    }
    
}
