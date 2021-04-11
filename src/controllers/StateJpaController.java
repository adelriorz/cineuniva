package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Municipality;
import java.util.ArrayList;
import java.util.List;
import entities.Billboard;
import entities.State;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StateJpaController implements Serializable {

    public StateJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public StateJpaController() {
        this.emf = Persistence.createEntityManagerFactory("cineUNIVAPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(State state) {
        if (state.getMunicipalityList() == null) {
            state.setMunicipalityList(new ArrayList<Municipality>());
        }
        if (state.getBillboardList() == null) {
            state.setBillboardList(new ArrayList<Billboard>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Municipality> attachedMunicipalityList = new ArrayList<Municipality>();
            for (Municipality municipalityListMunicipalityToAttach : state.getMunicipalityList()) {
                municipalityListMunicipalityToAttach = em.getReference(municipalityListMunicipalityToAttach.getClass(), municipalityListMunicipalityToAttach.getMunicipalityId());
                attachedMunicipalityList.add(municipalityListMunicipalityToAttach);
            }
            state.setMunicipalityList(attachedMunicipalityList);
            List<Billboard> attachedBillboardList = new ArrayList<Billboard>();
            for (Billboard billboardListBillboardToAttach : state.getBillboardList()) {
                billboardListBillboardToAttach = em.getReference(billboardListBillboardToAttach.getClass(), billboardListBillboardToAttach.getBillboardId());
                attachedBillboardList.add(billboardListBillboardToAttach);
            }
            state.setBillboardList(attachedBillboardList);
            em.persist(state);
            for (Municipality municipalityListMunicipality : state.getMunicipalityList()) {
                State oldStateIdOfMunicipalityListMunicipality = municipalityListMunicipality.getStateId();
                municipalityListMunicipality.setStateId(state);
                municipalityListMunicipality = em.merge(municipalityListMunicipality);
                if (oldStateIdOfMunicipalityListMunicipality != null) {
                    oldStateIdOfMunicipalityListMunicipality.getMunicipalityList().remove(municipalityListMunicipality);
                    oldStateIdOfMunicipalityListMunicipality = em.merge(oldStateIdOfMunicipalityListMunicipality);
                }
            }
            for (Billboard billboardListBillboard : state.getBillboardList()) {
                State oldStateIdOfBillboardListBillboard = billboardListBillboard.getStateId();
                billboardListBillboard.setStateId(state);
                billboardListBillboard = em.merge(billboardListBillboard);
                if (oldStateIdOfBillboardListBillboard != null) {
                    oldStateIdOfBillboardListBillboard.getBillboardList().remove(billboardListBillboard);
                    oldStateIdOfBillboardListBillboard = em.merge(oldStateIdOfBillboardListBillboard);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(State state) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            State persistentState = em.find(State.class, state.getStateId());
            List<Municipality> municipalityListOld = persistentState.getMunicipalityList();
            List<Municipality> municipalityListNew = state.getMunicipalityList();
            List<Billboard> billboardListOld = persistentState.getBillboardList();
            List<Billboard> billboardListNew = state.getBillboardList();
            List<String> illegalOrphanMessages = null;
            for (Municipality municipalityListOldMunicipality : municipalityListOld) {
                if (!municipalityListNew.contains(municipalityListOldMunicipality)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Municipality " + municipalityListOldMunicipality + " since its stateId field is not nullable.");
                }
            }
            for (Billboard billboardListOldBillboard : billboardListOld) {
                if (!billboardListNew.contains(billboardListOldBillboard)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Billboard " + billboardListOldBillboard + " since its stateId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Municipality> attachedMunicipalityListNew = new ArrayList<Municipality>();
            for (Municipality municipalityListNewMunicipalityToAttach : municipalityListNew) {
                municipalityListNewMunicipalityToAttach = em.getReference(municipalityListNewMunicipalityToAttach.getClass(), municipalityListNewMunicipalityToAttach.getMunicipalityId());
                attachedMunicipalityListNew.add(municipalityListNewMunicipalityToAttach);
            }
            municipalityListNew = attachedMunicipalityListNew;
            state.setMunicipalityList(municipalityListNew);
            List<Billboard> attachedBillboardListNew = new ArrayList<Billboard>();
            for (Billboard billboardListNewBillboardToAttach : billboardListNew) {
                billboardListNewBillboardToAttach = em.getReference(billboardListNewBillboardToAttach.getClass(), billboardListNewBillboardToAttach.getBillboardId());
                attachedBillboardListNew.add(billboardListNewBillboardToAttach);
            }
            billboardListNew = attachedBillboardListNew;
            state.setBillboardList(billboardListNew);
            state = em.merge(state);
            for (Municipality municipalityListNewMunicipality : municipalityListNew) {
                if (!municipalityListOld.contains(municipalityListNewMunicipality)) {
                    State oldStateIdOfMunicipalityListNewMunicipality = municipalityListNewMunicipality.getStateId();
                    municipalityListNewMunicipality.setStateId(state);
                    municipalityListNewMunicipality = em.merge(municipalityListNewMunicipality);
                    if (oldStateIdOfMunicipalityListNewMunicipality != null && !oldStateIdOfMunicipalityListNewMunicipality.equals(state)) {
                        oldStateIdOfMunicipalityListNewMunicipality.getMunicipalityList().remove(municipalityListNewMunicipality);
                        oldStateIdOfMunicipalityListNewMunicipality = em.merge(oldStateIdOfMunicipalityListNewMunicipality);
                    }
                }
            }
            for (Billboard billboardListNewBillboard : billboardListNew) {
                if (!billboardListOld.contains(billboardListNewBillboard)) {
                    State oldStateIdOfBillboardListNewBillboard = billboardListNewBillboard.getStateId();
                    billboardListNewBillboard.setStateId(state);
                    billboardListNewBillboard = em.merge(billboardListNewBillboard);
                    if (oldStateIdOfBillboardListNewBillboard != null && !oldStateIdOfBillboardListNewBillboard.equals(state)) {
                        oldStateIdOfBillboardListNewBillboard.getBillboardList().remove(billboardListNewBillboard);
                        oldStateIdOfBillboardListNewBillboard = em.merge(oldStateIdOfBillboardListNewBillboard);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = state.getStateId();
                if (findState(id) == null) {
                    throw new NonexistentEntityException("The state with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            State state;
            try {
                state = em.getReference(State.class, id);
                state.getStateId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The state with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Municipality> municipalityListOrphanCheck = state.getMunicipalityList();
            for (Municipality municipalityListOrphanCheckMunicipality : municipalityListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This State (" + state + ") cannot be destroyed since the Municipality " + municipalityListOrphanCheckMunicipality + " in its municipalityList field has a non-nullable stateId field.");
            }
            List<Billboard> billboardListOrphanCheck = state.getBillboardList();
            for (Billboard billboardListOrphanCheckBillboard : billboardListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This State (" + state + ") cannot be destroyed since the Billboard " + billboardListOrphanCheckBillboard + " in its billboardList field has a non-nullable stateId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(state);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<State> findStateEntities() {
        return findStateEntities(true, -1, -1);
    }

    public List<State> findStateEntities(int maxResults, int firstResult) {
        return findStateEntities(false, maxResults, firstResult);
    }

    private List<State> findStateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(State.class));
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

    public State findState(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(State.class, id);
        } finally {
            em.close();
        }
    }

    public int getStateCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<State> rt = cq.from(State.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
