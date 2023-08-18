package com.rentkaro.pojos;

import lombok.Getter;

//@Entity
//@Getter
//@Table(name = "category")
public enum Category {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long categoryId;
//	@Column(name = "category_name")
//	private String categoryName;
//	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
//	private List<Product> categoryProductsList= new ArrayList<Product>();
	FUNITURE,MOBILE,KITCHEN
}
