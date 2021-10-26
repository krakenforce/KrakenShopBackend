package com.krakenforce.app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.model.CartItem;
import com.krakenforce.app.model.Product;
import com.krakenforce.app.model.ShoppingCart;
import com.krakenforce.app.security.common.MessageResponse;
import com.krakenforce.app.service.CartItemService;
import com.krakenforce.app.service.ProductService;
import com.krakenforce.app.service.ShoppingCartService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
	
	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ProductService productService;
	
	@PostMapping()
	public ResponseEntity<?> addItemToCart(@RequestParam("cartId") int cartId,
			@RequestParam("productId") int productId,
			@RequestParam("quantity") int quantity){
		try {
			CartItem item = new CartItem();
			Product selectedProduct = productService.getById(productId);
			ShoppingCart cart = shoppingCartService.getById(cartId);		
			Set<CartItem> cartItems = cart.getCartItems();
			int totalPrice = 0;
			for(CartItem itemhe : cartItems) {
				if(itemhe.getProduct().equals(selectedProduct)) {
					item.setQuantity(itemhe.getQuantity() + quantity);
					item.setSubTotal(itemhe.getSubTotal() + (quantity * item.getQuantity()));
					break;
				}else {
					item.setQuantity(quantity);
					item.setSubTotal(selectedProduct.getSalePrice() * quantity);
					item.setProduct(selectedProduct);
					item.setShoppingCart(cart);
					break;
				}
				
			}
			
			cartItemService.add(item);
			
			
			return new ResponseEntity<MessageResponse>(new MessageResponse("add success"), new HttpHeaders(), HttpStatus.BAD_REQUEST);		
			
		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("add fail"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

}
