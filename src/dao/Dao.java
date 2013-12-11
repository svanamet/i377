package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.Unit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Dao {
	
	 @PersistenceContext
	 private EntityManager em;

	public List<Unit> search(String search) {

			TypedQuery<Unit> query = em.createQuery("select u from Unit u where upper(u.name) like upper('%" + search + "%')", Unit.class);

			return query.getResultList();

	}

	public List<Unit> findAllUnits() {

			TypedQuery<Unit> query = em.createQuery(
					"select u from Unit u", Unit.class);

			return query.getResultList();

	}

	public List<String> findAllCodes(Long id){

		List<Unit> units = findAllUnits();
		List<String> codes = new ArrayList<String>();

		for(Unit unit : units){
			if(unit.getSuper_unit_id() == id){
				codes.add(unit.getCode()); 
			}
		}
		return codes;
	}
	@Transactional
	public Unit findById(Long id) {

			Unit unit = new Unit();
			if(id != null){
				unit = em.find(Unit.class, id);
			}
			return unit;

	}
	@Transactional
	public Unit findByCode(String code) {

			TypedQuery<Unit> query = em.createQuery("select u from Unit u where u.code = :code", Unit.class);
			query.setParameter("code", code);

			return query.getSingleResult();

	}
	
	@Transactional
	public void addUnit(Unit unit) {
			
			if (unit.getId() == null) {
				em.persist(unit);
			} else {
				em.merge(unit);
			}

	}
	@Transactional
	public void deleteAllUnits(){

			Query query = em.createQuery("delete from Unit");
			query.executeUpdate();


	}
	@Transactional
	public void deleteUnit(Long id){
	
		Unit unit = em.find(Unit.class, id);
			if (unit != null)
				em.remove(unit);

	}
	@Transactional
	public List<Unit> findSubUnitsById(Long id) {
		  TypedQuery<Unit> query = em.createQuery("select u from Unit u where u.super_unit_id = "+id,
		    Unit.class);

		  System.out.println(query.getResultList());
		  return query.getResultList();
		  
		 }

}