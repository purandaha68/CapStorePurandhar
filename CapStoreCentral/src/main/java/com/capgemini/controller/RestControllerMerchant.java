package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.capgemini.dao.DAOLayerAdmin;
import com.capgemini.dao.DAOLayerImplAdmin;
import com.capgemini.dto.Admin;
import com.capgemini.dto.Customers;
import com.capgemini.dto.Merchants;
import com.capgemini.dto.ProductListDummy;
import com.capgemini.dto.Products;
import com.capgemini.exception.CapStoreException;
import com.capgemini.service.ServiceLayer;
import com.capgemini.service.ServiceLayerMerchant;



@RestController
public class RestControllerMerchant {
	
	@Autowired
	ServiceLayerMerchant ser;
	
	
	@RequestMapping(value="/loginMerchant",method=RequestMethod.POST)
	public Merchants loginMerchant(@RequestBody Merchants merchant){
		
		merchant=ser.validateMerchantForLogin(merchant);
		return merchant;
	
	}
	
	@RequestMapping(value="/addmerchant",method=RequestMethod.POST)
	public Merchants signUp(@RequestBody Merchants mer){
	
		mer=ser.addMerchant(mer);
		System.out.println(mer.getMerchantName());
		return mer;
	}
	

 
	
	@RequestMapping(value="/deletemerchant", method=RequestMethod.POST)
	public Merchants deleteMerchant(@RequestBody Merchants merchant){
		try {
			return ser.deleteMerchant(merchant);
		} catch (CapStoreException e) {
			e.printStackTrace();
		}
		return merchant;
	}
 
	@RequestMapping(value="/addproduct", method=RequestMethod.POST)
	public Products addproduct(@RequestBody Products p){
		
		System.out.println("Inside Rest Controller");
		p = ser.addProduct(p);
		System.out.println("Perfomed Rest Controller");
		return p;
	}
		
	 
	@RequestMapping(value="/displayAllProduct", method=RequestMethod.GET)
	public ProductListDummy allProducts(){
		ProductListDummy p = new ProductListDummy();
		p.setProducts(ser.getAllProduct());
		return p;
	}
	
	@RequestMapping(value="/findproduct", method=RequestMethod.GET)
	public Products findProduct( Integer id) throws CapStoreException {
		Products p = ser.findById(id);
		System.out.println(p.getProductName());
		return p;
//		return ser.updateProduct(p);
	/*	try {
			
			Products p = ser.findById(id);
			
			return ser.updateProduct(p);
			
		} catch (CapStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
	 
	}
	@RequestMapping(value="/updateproduct", method=RequestMethod.POST)
	public Products updateProduct(@RequestBody Products product) throws CapStoreException {
		
	//	System.out.println(p.getProductName());
	//	return p;
		System.out.println("in rest controller"+product.getProductName());
	return ser.updateProduct(product);
	/*	try {
			
			Products p = ser.findById(id);
			
			return ser.updateProduct(p);
			
		} catch (CapStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
	 
	}
	
	@RequestMapping(value="/deleteproduct", method=RequestMethod.POST)
	public Products deleteProduct( @RequestBody String[] ids){
	
		try {
		
			ser.deleteProduct(ids);
		

		} catch (CapStoreException e) {
			 
			e.printStackTrace();
		}
		return null;
	
		
	}
	
	
/*	@RequestMapping(value="/displayAllProduct", method=RequestMethod.GET)
	public List<Products> displayProducts(@RequestBody Products product){
		
		List<Products> lp= ser.getAllProduct();
		return lp;
		
	}*/
	
 
	
// 
//	
//	@RequestMapping(value="/displayAllMerchant", method=RequestMethod.GET)
//	public List<Merchants> displayMerchants(){
//		return serv.getAllMerchant();
//	}
	
	
//	
//	@RequestMapping(value="/updatemerchant", method=RequestMethod.POST)
//	public Merchants updateMerchant(@RequestBody Merchants merchant){
//		return serv.updateMerchant(merchant);
//	}
//	
//	@RequestMapping(value="/getAllCustomer", method=RequestMethod.GET)
//	public List<Customers> displayCustomers(){
//		return serv.getAllCustomer();
//	}
//	@RequestMapping(value="/checkavaliability", method=RequestMethod.POST)
//	public String checkAvailability(@RequestBody Products product){
//		if(serv.checkAvailabilty(product))
//			return "avaliable";
//		else
//			return "not avaliable";
//	}
//	
//	@RequestMapping(value="/checkdeliveryavaliability", method=RequestMethod.POST)
//	public String checkDeliveryAvaliability(@RequestBody Products product, @RequestBody int pincode){
//		if(serv.checkDeliveryAvailability(product, pincode))
//			return "available";
//		else
//			return "not avaliable";
//	}
	
	
	
}