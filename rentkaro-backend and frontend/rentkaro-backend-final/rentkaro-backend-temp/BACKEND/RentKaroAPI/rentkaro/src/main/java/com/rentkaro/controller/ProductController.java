package com.rentkaro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.rentkaro.dto.MyProductDTO;
import com.rentkaro.dto.SellProductDTO;
import com.rentkaro.service.ImageHandlingService;
import com.rentkaro.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductController {
	@Autowired
	public ProductService productService;
	
	@Autowired
	private ImageHandlingService imageService;
	
	RazorpayClient razorpay;

	public ProductController() {
		System.out.println("product controller bean created");
	}

	@PostMapping("/sell")
	public Long addProduct(@RequestBody SellProductDTO product, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");

		return productService.addProduct(id,product);
	}
	
	@GetMapping("/{prodId}")
	public MyProductDTO displayProduct(@PathVariable Long prodId, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		MyProductDTO prod = productService.displayProduct(prodId);
		if(id.equals(prod.getOwnerId())) {
			return prod;
		}
		else {
			prod.setOwnerId(null);
			return prod;
		}
	}
	
	@GetMapping("/myproduct/{prodId}")
	public MyProductDTO displayMyProduct(@PathVariable Integer prodId, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		MyProductDTO prod = productService.displayMyProduct(id,prodId);
		return prod;
	}
	
	@GetMapping("/makepayment/{ammount}")
	public String makePayment(@PathVariable Double ammount) throws RazorpayException {	
		razorpay = new RazorpayClient("rzp_test_YEzPPX99Po9GMr", "rW4AVWvXFQjPBUBloyn1v5Jh");
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", ammount*100); // amount in the smallest currency unit
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", "test");
		orderRequest.put("payment_capture", true);

		return razorpay.orders.create(orderRequest).get("id");
	}
	
	@GetMapping("/rent/{prodId}/{transactionId}")
	public String rentProduct(@PathVariable Long prodId,@PathVariable String transactionId, HttpServletRequest request) {
		Long id = (Long)request.getSession().getAttribute("id");
		Double ammount = productService.rentProduct(id,prodId,transactionId);
//		makePayment(ammount,transactionId);
		return "Product rented successfully!";
	}
	
	@PostMapping(value = "/prodimage", consumes = "multipart/form-data")
	public void uploadImage(@RequestParam Long prodid, @RequestParam MultipartFile image1, @RequestParam MultipartFile image2) throws IOException {
		imageService.uploadProdImage(prodid,image1, image2);
	}

	@GetMapping(value = "/prodimage")
	public List<byte[]> downloadImage(@RequestParam Long prodid) throws IOException {
		return imageService.serveProdImage(prodid);
	}
	
}
