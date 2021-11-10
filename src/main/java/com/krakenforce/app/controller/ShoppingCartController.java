package com.krakenforce.app.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakenforce.app.dtos.ShoppingCartDtos;
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
	public ResponseEntity<?> addItemToCart(@RequestParam("cartId") int cartId, @RequestParam("productId") int productId,
			@RequestParam("quantity") int quantity) {
		try {
			ShoppingCart shoppingCart = shoppingCartService.getById(cartId);
			Product product = productService.getById(productId);
			int productQuantity = quantity;
			Set<CartItem> cartItemSet = shoppingCart.getCartItems();

			if (cartItemSet.isEmpty()) {
				CartItem newItem = new CartItem();
				newItem.setProduct(product);
				newItem.setQuantity(productQuantity);
				newItem.setShoppingCart(shoppingCart);
				newItem.setSubTotal(productQuantity * product.getSalePrice());
				cartItemService.add(newItem);
			} else {
				CartItem newItem = new CartItem();
				for (CartItem item : cartItemSet) {
					if (item.getProduct().equals(product)) {
						newItem = item;
						newItem.setQuantity(item.getQuantity() + productQuantity);
						newItem.setSubTotal(item.getSubTotal() + (product.getSalePrice() * productQuantity));
						break;
					} else {
						newItem.setProduct(product);
						newItem.setQuantity(productQuantity);
						newItem.setShoppingCart(shoppingCart);
						newItem.setSubTotal(productQuantity * product.getSalePrice());
					}
				}
				cartItemService.add(newItem);
			}

			int cartQuantity = 0;
			float cartTotal = 0;
			Set<CartItem> newCartItemSet = shoppingCart.getCartItems();
			for (CartItem item : newCartItemSet) {
				cartQuantity += item.getQuantity();
				cartTotal += item.getSubTotal();
			}
			shoppingCart.setTotal(cartTotal);
			shoppingCart.setQuantity(cartQuantity);
			shoppingCartService.add(shoppingCart);

			return new ResponseEntity<MessageResponse>(new MessageResponse("Add Success"), new HttpHeaders(),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Can not found product"), new HttpHeaders(),
					HttpStatus.OK);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ShoppingCartDtos> getCartByUserId(@PathVariable("userId") int userId) {
		try {
			ShoppingCartDtos response = shoppingCartService.getByUser(userId);
			return new ResponseEntity<ShoppingCartDtos>(response, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ShoppingCartDtos>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

}
