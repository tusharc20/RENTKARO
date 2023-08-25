import React, { useEffect, useState } from "react";
import Notch from "./Notch";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  useNavigate,
} from "react-router-dom";
import axios from "axios";
import HomeHeader from "./HomeHeader";
import Footer from "./Footer";
import { Formik, useFormik } from "formik";
import { Toast } from "bootstrap";

export default function SellProduct() {
  const [productName, setProdName] = useState("");
  const [productFeatures, setProdFeature] = useState("");
  const [productDescription, setProdDesc] = useState("");
  const [rentalPrice, setRent] = useState("");
  const [deposite, setDeposite] = useState("");
  const [category, setCategory] = useState("Furnitures");
  const [image, setImage] = useState("");
  let navigate = useNavigate();
  useEffect(() => {
    isLogin();
  }, []);
  async function isLogin() {
    await axios
      .get("http://localhost:8080/user/islogin", { withCredentials: true })
      .then((resp) => {
        if (resp.data == "") navigate("/");
      })
      .catch((err) => {
        navigate("/");
      });
  }

  //handling file changing event
  const handleFileChange = (event) => {
    setImage(event.target.files[0]);
    console.log(event.target.files[0]);
  };

  const uploadPostImage = (image, productId) => {
    //durgesh image handling code
    {
      let formData = new FormData();
      console.log(image);
      formData.append("image", image);
      return axios
        .post(
          `http://localhost:8080/products/add/image/${productId}`,
        //   {},

          formData,
          {
            withCredentials: true,

            headers: {
              "Content-Type": "multipart/form-data",
              "Access-Control-Allow-Origin": "http://localhost:8080/products/add/image/",
            },
            // mode: "cors",
          }
        )
        .then((response) => {
          //   console.log(response.data + " dfdshfjhsdjfkh");
          return response.data;
        })
      .catch((error) => console.log(error));
    }
  };

  function sellProd(e) {
    e.preventDefault();
    const product = {
      productName,
      productFeatures,
      productDescription,
      rentalPrice,
      deposite,
      category,
    };
    axios
      .post("http://localhost:8080/product/sell", product, {
        withCredentials: true,
      })
      .then((resp) => {
        console.log(resp.data);
        const id = uploadPostImage(image, resp)
          .then((data) => {
            console.log(data + " this is datta");
            alert("Image uploaded!");
          })
          .catch((error) => {
            alert("error in uploading image");
            console.log(error);
          });

        const flag = window.confirm(
          "Product Sold Successfully... Do you want to go MyProduct page"
        );
        if (flag) navigate("/myproducts");
        else {
          setProdName("");
          setProdFeature("");
          setProdDesc("");
          setRent("");
          setDeposite("");
          setCategory("Furnitures");
        }
      })
      .catch((err) => {
        alert(err.data);
      });
  }
  return (
    <>
      <HomeHeader />
      <div className="container my-5">
        <div className="row justify-content-center">
          <h3>Sell Product</h3>
          <hr />
          <form onSubmit={sellProd}>
            <div className="form-outline mb-4">
              <input
                type="text"
                id="form6Example5"
                className="form-control"
                required
                value={productName}
                onChange={(e) => setProdName(e.target.value)}
              />
              <label className="form-label" htmlFor="form6Example5">
                Product Name
              </label>
            </div>

            <div className="form-outline mb-4">
              <input
                type="text"
                id="form6Example3"
                className="form-control"
                value={productFeatures}
                required
                onChange={(e) => setProdFeature(e.target.value)}
              />
              <label className="form-label" htmlFor="form6Example3">
                Product Feature
              </label>
            </div>

            <div className="form-outline mb-4">
              <textarea
                className="form-control"
                id="form6Example7"
                rows="4"
                value={productDescription}
                required
                onChange={(e) => setProdDesc(e.target.value)}
              ></textarea>
              <label className="form-label" htmlFor="form6Example7">
                Product Description
              </label>
            </div>

            <div className="row mb-4">
              <div className="col">
                <div className="form-outline">
                  <input
                    type="number"
                    id="form6Example1"
                    className="form-control"
                    required
                    value={rentalPrice}
                    onChange={(e) => setRent(e.target.value)}
                  />
                  <label className="form-label" htmlFor="form6Example1">
                    Rental Price
                  </label>
                </div>
              </div>
              <div className="col">
                <div className="form-outline">
                  <input
                    type="number"
                    id="form6Example1"
                    className="form-control"
                    required
                    value={deposite}
                    onChange={(e) => setDeposite(e.target.value)}
                  />
                  <label className="form-label" htmlFor="form6Example1">
                    deposite
                  </label>
                </div>
              </div>
            </div>

            <select
              className="form-select mb-4 form-outline"
              aria-label="Default select example"
              required
              value={category}
              onChange={(e) => setCategory(e.target.value)}
            >
              <option selected value="Furnitures">
                Furnitures
              </option>
              <option value="Vehicles">Vehicles</option>
              <option value="Electronics">Electronics</option>
              <option value="clothes">Clothes</option>
              <option value="Others">Others</option>
            </select>
            <label class="form-label" for="customFile">
              Default file input example
            </label>
            <input
              onChange={handleFileChange}
              type="file"
              accept="image/*"
              class="form-control"
              id="customFile"
              name="imagePath"
            />

            <button type="submit" className="btn btn-primary btn-block mb-4">
              Sell Product
            </button>
          </form>
        </div>
      </div>
      <Footer />
    </>
  );
}
