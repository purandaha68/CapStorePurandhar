package com.capgemini.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dto.Admin;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.Products;
import com.capgemini.exception.CapStoreException;

@Repository
@EnableTransactionManagement
public class DAOLayerImplMerchant implements DAOLayerMerchant {
	

	@PersistenceContext
	private EntityManager em;
	
	
	
	@Override
	public Merchants findByEmailID (String email_ID) {
		String sql="select merchant from Merchants merchant where merchant.merchantEmail =:email" ;
		TypedQuery<Merchants> tq = em.createQuery(sql,Merchants.class);
		tq.setParameter("email", email_ID);
	
		return tq.getSingleResult();
		
	}
	
	@Override
	public Merchants validateMerchantForLogin(Merchants merchant) {
		
		Merchants check=findByEmailID(merchant.getMerchantEmail());
		if(check.getMerchantPswd().equals(merchant.getMerchantPswd()))
			return check;
		else
			return null;
	}
	
	
	@Override
	public Merchants addMerchant(Merchants merchant) {
		
			em.persist(merchant);
			return merchant;

	}

	@Override
	public Merchants deleteMerchant(Merchants merchant) {
		em.remove(merchant);
		return merchant;
	}
	
	@Override
	public Merchants updateMerchant(Merchants merchant) {
		Merchants m=em.find(Merchants.class,merchant.getMerchantId());
		//pending
		return m;
	}
	
	@Override
	@Transactional
	public Products addProduct(Products product) {

			em.persist(product);
			return product;	
	}
	
	@Override
	public void deleteProduct(String ids[]) {
		
		//Products pid = em.find(Products.class, ids);
		
		for(String s : ids)
		{
			int i = Integer.parseInt(s);
			Products p  = em.find(Products.class, i);
			em.remove(p);
		} 
		  
	}
	
	@Override
	public Products updateProduct(Products product) {
		/*Products p=em.find(Products.class, product.getProductId());
		p.setProductCategory(product.getProductCategory());
		p.setProductName(product.getProductName());
		p.setProductPrice(product.getProductPrice());
		p.setProductQuantity(product.getProductQuantity());
		p.setProductRating(product.getProductRating());
		p.setTotalSold(product.getTotalSold());*/
		em.merge(product);
		return product;
	}


	
	@Override
	public List<Merchants> getAllMerchant() {
		String sql="select merchant from Merchants merchant";
		javax.persistence.TypedQuery<Merchants> tq=em.createQuery(sql, Merchants.class);
		List<Merchants> lm=tq.getResultList();
		return lm;
	}

	@Override
	public List<Customers> getAllCustomer() {
		String sql="select customer from Customer customer";
		TypedQuery<Customers> tq=em.createQuery(sql,Customers.class);
		List<Customers> lm=tq.getResultList();
		return lm;
	}

	@Override
	public List<Products> getAllProduct() {
		String sql="select product from Products product";
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		List<Products> lm=tq.getResultList();
		return lm;
	}

	@Override
	public List<Products> getAllProduct(int merchantId) {
		String sql="select product from Product product join product.merchants merchants where merchants.merchant_id in (:id) ";
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		tq.setParameter("id", merchantId);
		List<Products> lm=tq.getResultList();
		return lm;
	}
	
	
	@Override
	public boolean checkAvailabilty(Products product) {
		Products p=em.find(Products.class,product.getProductId());
		if(p.getProductQuantity()-1<0)
			return true;
		else
			return false;
	}
	@Override
	public boolean checkDeliveryAvailability(Products product, int pincode) throws CapStoreException {
		
		String sql="select pincode from mp from MerchantPincode mp where merchant_id in"
				+ "(select merchants.merchant_id from  Product p join p.merchants merchants where p.product_id in (:id))";
		
		TypedQuery<Integer> tq=em.createQuery(sql,Integer.class);
		tq.setParameter("id", product.getProductId());
		List<Integer> pin=tq.getResultList();
		for(Integer p:pin){
			if(p.equals(pincode))
				return true;
		}
		
		return false;
	}

	@Override
	public Products findById(int id) {
		
		return em.find(Products.class, id);
	  
	}
	

 
	
}
