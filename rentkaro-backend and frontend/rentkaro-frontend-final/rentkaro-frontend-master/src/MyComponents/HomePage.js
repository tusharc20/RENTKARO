import '../App.css';
import HomeHeader from "./HomeHeader";
import NavBar from "./NavBar";
import HomeBody from "./HomeBody";
import Footer from "./Footer";
import React, { useEffect, useState } from 'react'
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom"
import axios from "axios"

function HomePage() {
    let navigate = useNavigate()
    const [products, setProducts] = useState([])
    const [productsLst, setProductsLst] = useState([])
    useEffect(() => {
        loadProducts()
    }, [])
    const loadProducts = async () => {
        await axios.get("http://localhost:8080/home", { withCredentials: true }).then((resp) => {
            setProducts(resp.data)
            setProductsLst(resp.data)
        }).catch((err) => {
            navigate("/")
        })
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
    return (
        <>
            <header>
                <HomeHeader search={search} />
                <NavBar />
            </header>
            <section>
                <HomeBody prod={products} />
            </section>
            <footer id="footer">
                <Footer />
            </footer>
        </>
    );
}

export default HomePage;
