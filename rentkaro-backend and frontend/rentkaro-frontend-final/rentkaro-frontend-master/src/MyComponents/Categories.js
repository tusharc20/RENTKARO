import React, { useEffect, useState } from 'react'
import HomeHeader from './HomeHeader'
import ShowFilter from './ShowFilter'
import RelatedItems from './RelatedItems'
import Price from './Price'
import Rating from './Rating'
import Sort from './Sort'
import Product from './Product'
import Nav from './Nav'
import Footer from './Footer'
import axios from "axios"
import { BrowserRouter as Router, Routes, Route, useNavigate, useLocation } from "react-router-dom"

function Categories() {
    let navigate = useNavigate()
    const location = useLocation();
    const [products, setProducts] = useState([])
    const [productsLst, setProductsLst] = useState([])
    const [cat, setCat] = useState('')
    useEffect(() => {
        if (cat != category) {
            getProducts()
            setCat(category)
        }
    }, [location.search])
    const queryParameters = new URLSearchParams(window.location.search)
    const category = queryParameters.get("category")
    const num = queryParameters.get("num")
    const getProducts = async () => {
        await axios.get("http://localhost:8080/home/" + category, { withCredentials: true }).then((resp) => {
            setProducts(resp.data)
            setProductsLst(resp.data)
        }).catch((err) => {
            navigate("/")
        })
    }
    const option = (opt) => {
        if (opt == 0) {
            const sortedItems = [...products].sort((a, b) => a.productId - b.productId)
            setProducts(sortedItems)
        }
        else if (opt == 1) {
            const sortedItems = [...products].sort((a, b) => a.rentalPrice - b.rentalPrice)
            setProducts(sortedItems)
        }
        else if (opt == 2) {
            const sortedItems = [...products].sort((b, a) => a.productId - b.productId)
            setProducts(sortedItems)
        }
        else if (opt == 3) {
            const sortedItems = [...productsLst].sort((a, b) =>  (a.rating!=null && b.rating==null) || (a.rating - b.rating)).reverse()
            setProducts(sortedItems)
        }
    }
    const search = (str) => {
        if (str !== "") {
            let searchProd = [...productsLst]
            const searchByName = searchProd.filter((obj) => obj.productName.toLowerCase().includes(str))
            searchProd = [...productsLst].filter(obj1 => !searchByName.some(obj2 => obj1.productId == obj2.productId))
            const searchByDesc = searchProd.filter((obj) => obj.productDescription.toLowerCase().includes(str))
            searchProd = [...productsLst].filter(obj1 => !searchByDesc.some(obj2 => obj1.productId == obj2.productId))
            const searchByFeature = searchProd.filter((obj) => obj.productFeatures.toLowerCase().includes(str))
            searchProd = [...productsLst].filter(obj1 => !searchByFeature.some(obj2 => obj1.productId == obj2.productId))
            const searchByCategory = searchProd.filter((obj) => obj.category.toLowerCase().includes(str))
            setProducts([...searchByName, ...searchByDesc, ...searchByFeature, ...searchByCategory])
        }
    }
    const range = (min, max) => {
        const sortedItems = [...productsLst].filter((obj) => (obj.rentalPrice+obj.deposite)>=min && (obj.rentalPrice+obj.deposite)<=max)
        setProducts(sortedItems)
    }
    return (
        <>
            <HomeHeader search={search} />

            <section className="mt-5">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-3">
                            <ShowFilter />
                            <div className="collapse card d-lg-block mb-5" id="navbarSupportedContent">
                                <div className="accordion" id="accordionPanelsStayOpenExample">
                                    <RelatedItems />
                                    <Price range={range} />
                                    <Rating />
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-1"></div>
                        <div className="col-lg-8">
                            <Sort cat={category} len={products.length} optionFun={option} />
                            <Product prod={products} num={num} />
                            <hr />

                            <Nav len={parseInt(products.length / 5)} cat={category} num={num} />
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </>
    )
}

export default Categories