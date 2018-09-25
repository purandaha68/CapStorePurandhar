package com.capgemini.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.capgemini.dto.Carts;
import com.capgemini.dto.CustomerOrders;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Invoice;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.OrderEntries;
import com.capgemini.dto.Products;
import com.capgemini.dto.WishList;


@Repository
public class DAOLayerImpl implements DAOLayer {

	
	@PersistenceContext
	EntityManager em;
	/**************our dao implementation class**********************************************/
	@Override
	public List<Merchants> getAllMerchant() {
		String sql="select merchant from Merchants merchant";
		javax.persistence.TypedQuery<Merchants> tq=em.createQuery(sql, Merchants.class);
		List<Merchants> lm=tq.getResultList();
		return lm;
	}

	@Override
	public List<Customers> getAllCustomer() {
		String sql="select customer from Customers customer";
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
		String sql="select product from Products product join product.merchants merchants where merchants.merchant_id in (:id) ";
		TypedQuery<Products> tq=em.createQuery(sql,Products.class);
		tq.setParameter("id", merchantId);
		List<Products> lm=tq.getResultList();
		return lm;
	}
	

	@Override
	public boolean addProduct(Products product) {
		
		try{
			em.persist(product);
		}catch(Exception e)
		{
			System.out.println("an exception in dao layer");
			return false;
		}
		return true;
	}

	@Override
	public Products deleteProduct(Products product) {
		em.remove(product);
		return product;
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
	public boolean addMerchant(Merchants merchant) {
		try{
			em.persist(merchant);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}

	@Override
	public Merchants deleteMerchant(Merchants merchant) {
		em.remove(merchant);
		return merchant;
	}

	@Override
	public Merchants updateMerchant(Merchants merchant) {
		/*Merchants m=em.find(Merchants.class,merchant.getMerchantId());
		m.setMerchantAddress(merchant.getMerchantAddress());
		m.setMerchantEmail(merchant.getMerchantEmail());
		m.setMerchantFlag(merchant.getMerchantFlag());
		m.setMerchantMobile(merchant.getMerchantMobile());
		m.setMerchantName(merchant.getMerchantName());
		m.setMerchantPswd(merchant.getMerchantPswd());
		m.setMerchantRating(merchant.getMerchantRating());
		m.setMerchanttype(merchant.getMerchanttype());*/
		em.merge(merchant);
		return merchant;
	}

	@Override
	public boolean checkAvailabilty(Products product) {
		Products p=em.find(Products.class,product.getProductId());
		if(p.getProductQuantity()-1<0)
			return false;
		else
			return true;
	}

	@Override
	public boolean checkDeliveryAvailability(Products product, int pincode) {
		
		
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

	/***********************************our functions end*******************************/
	
	public Carts addToCart(int productId, int customerId, int quantity, int merchantId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Carts removeFromCart(int productId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Carts emptyCart(int cartId) {
		int i=1;
		return null;
	}

	public Carts saveCart(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Carts retrieveCart(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> selectAddress(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders retrieveAddress(int customerId, int addressId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders addCoupon(int couponId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders removeCoupon(int couponId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderEntries retrieveOrderDetails(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public CustomerOrders changeStatus(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Invoice generateInvoice(int orderId, int customerId, int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Invoice makePayment(int amount, int orderId, int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WishList addToWishList(int productId, int customerId, int merchantId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WishList removeFromWishList(int wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WishList emptyWishList(int wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WishList saveWishList(int wishlistId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WishList retrieveWishList(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createAccount(Customers cusDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Products> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean login(String email_ID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String forgotPassword(String email_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customers findByEmailID(String email_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customers findByCustomerID(int customer_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean changePassword(String email_ID, String password, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

}
