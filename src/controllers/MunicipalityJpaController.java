/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.State;
import entities.Assistance;
import entities.Municipality;
import entities.MunicipalityPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Armando Del Rio
 */
public class MunicipalityJpaController implements Serializable {

    public MunicipalityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipality municipality) throws PreexistingEntityException, Exception {
        if (municipality.getMunicipalityPK() == null) {
            municipality.setMunicipalityPK(new MunicipalityPK());
        }
        if (municipality.getAssistanceList() == null) {
            municipality.setAssistanceList(new ArrayList<Assistance>());
        }
        municipality.getMunicipalityPK().setStateId(municipality.getState().getStateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            State state = municipality.getState();
            if (state != null) {
                state = em.getReference(state.getClass(), state.getStateId());
                municipality.setState(state);
            }
            List<Assistance> attachedAssistanceList = new ArrayList<Assistance>();
            for (Assistance assistanceListAssistanceToAttach : municipality.getAssistanceList()) {
                assistanceListAssistanceToAttach = em.getReference(assistanceListAssistanceToAttach.getClass(), assistanceListAssistanceToAttach.getAssistancePK());
                attachedAssistanceList.add(assistanceListAssistanceToAttach);
            }
            municipality.setAssistanceList(attachedAssistanceList);
            em.persist(municipality);
            if (state != null) {
                state.getMunicipalityList().add(municipality);
                state = em.merge(state);
            }
            for (Assistance assistanceListAssistance : municipality.getAssistanceList()) {
                Municipality oldMunicipalityOfAssistanceListAssistance = assistanceListAssistance.getMunicipality();
                assistanceListAssistance.setMunicipality(municipality);
                assistanceListAssistance = em.merge(assistanceListAssistance);
                if (oldMunicipalityOfAssistanceListAssistance != null) {
                    oldMunicipalityOfAssistanceListAssistance.getAssistanceList().remove(assistanceListAssistance);
                    oldMunicipalityOfAssistanceListAssistance = em.merge(oldMunicipalityOfAssistanceListAssistance);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMunicipality(municipality.getMunicipalityPK()) != null) {
                throw new PreexistingEntityException("Municipality " + municipality + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipality municipality) throws IllegalOrphanException, NonexistentEntityException, Exception {
        municipality.getMunicipalityPK().setStateId(municipality.getState().getStateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipality persistentMunicipality = em.find(Municipality.class, municipality.getMunicipalityPK());
            State stateOld = persistentMunicipality.getState();
            State stateNew = municipality.getState();
            List<Assistance> assistanceListOld = persistentMunicipality.getAssistanceList();
            List<Assistance> assistanceListNew = municipality.getAssistanceList();
            List<String> illegalOrphanMessages = null;
            for (Assistance assistanceListOldAssistance : assistanceListOld) {
                if (!assistanceListNew.contains(assistanceListOldAssistance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Assistance " + assistanceListOldAssistance + " since its municipality field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (stateNew != null) {
                stateNew = em.getReference(stateNew.getClass(), stateNew.getStateId());
                municipality.setState(stateNew);
            }
            List<Assistance> attachedAssistanceListNew = new ArrayList<Assistance>();
            for (Assistance assistanceListNewAssistanceToAttach : assistanceListNew) {
                assistanceListNewAssistanceToAttach = em.getReference(assistanceListNewAssistanceToAttach.getClass(), assistanceListNewAssistanceToAttach.getAssistancePK());
                attachedAssistanceListNew.add(assistanceListNewAssistanceToAttach);
            }
            assistanceListNew = attachedAssistanceListNew;
            municipality.setAssistanceList(assistanceListNew);
            municipality = em.merge(municipality);
            if (stateOld != null && !stateOld.equals(stateNew)) {
                stateOld.getMunicipalityList().remove(municipality);
                stateOld = em.merge(stateOld);
            }
            if (stateNew != null && !stateNew.equals(stateOld)) {
                stateNew.getMunicipalityList().add(municipality);
                stateNew = em.merge(stateNew);
            }
            for (Assistance assistanceListNewAssistance : assistanceListNew) {
                if (!assistanceListOld.contains(assistanceListNewAssistance)) {
                    Municipality oldMunicipalityOfAssistanceListNewAssistance = assistanceListNewAssistance.getMunicipality();
                    assistanceListNewAssistance.setMunicipality(municipality);
                    assistanceListNewAssistance = em.merge(assistanceListNewAssistance);
                    if (oldMunicipalityOfAssistanceListNewAssistance != null && !oldMunicipalityOfAssistanceListNewAssistance.equals(municipality)) {
                        oldMunicipalityOfAssistanceListNewAssistance.getAssistanceList().remove(assistanceListNewAssistance);
                        oldMunicipalityOfAssistanceListNewAssistance = em.merge(oldMunicipalityOfAssistanceListNewAssistance);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MunicipalityPK id = municipality.getMunicipalityPK();
                if (findMunicipality(id) == null) {
                    throw new NonexistentEntityException("The municipality with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MunicipalityPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Municipality municipality;
            try {
                municipality = em.getReference(Municipality.class, id);
                municipality.getMunicipalityPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipality with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Assistance> assistanceListOrphanCheck = municipality.getAssistanceList();
            for (Assistance assistanceListOrphanCheckAssistance : assistanceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Municipality (" + municipality + ") cannot be destroyed since the Assistance " + assistanceListOrphanCheckAssistance + " in its assistanceList field has a non-nullable municipality field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            State state = municipality.getState();
            if (state != null) {
                state.getMunicipalityList().remove(municipality);
                state = em.merge(state);
            }
            em.remove(municipality);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipality> findMunicipalityEntities() {
        return findMunicipalityEntities(true, -1, -1);
    }

    public List<Municipality> findMunicipalityEntities(int maxResults, int firstResult) {
        return findMunicipalityEntities(false, maxResults, firstResult);
    }

    private List<Municipality> findMunicipalityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipality.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Municipality findMunicipality(MunicipalityPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipality.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipalityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipality> rt = cq.from(Municipality.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
