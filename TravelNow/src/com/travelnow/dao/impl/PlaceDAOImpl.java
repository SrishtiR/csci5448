package com.travelnow.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.travelnow.dao_.PlacesDao;
import com.travelnow.model.PlaceModel;

@Repository("PlacesDao")
public class PlaceDAOImpl implements PlacesDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PlaceModel getPlaceByText(String text) {
		//List<?> result = hibernateTemplate.findByNamedParam("from PlaceModel p where p.city=:city", "city", text);

		
		DetachedCriteria detachedCriteria =  DetachedCriteria.forClass(PlaceModel.class);
		detachedCriteria.add(Restrictions.eq("city", text));
		List<PlaceModel> findByCriteria = (List<PlaceModel>) hibernateTemplate.findByCriteria(detachedCriteria);

		if(findByCriteria !=null && findByCriteria.size()>0)
		return  findByCriteria.get(0);
		else
			return null;
	}	
	


}
