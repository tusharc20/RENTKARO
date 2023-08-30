import React, { useEffect, useState } from 'react'
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom"
import axios from "axios"
import Notch from './Notch';

export default function ReturnPord() {
    let navigate = useNavigate()
    const [rating, setRating] = useState('')
    const [otp, setOtp] = useState('')
    const [errrating, setErrRating] = useState('')
    const [errotp, setErrOtp] = useState('')
    function generateOTP() {
        axios.get("http://localhost:8080/profile/returnproductrequest", { withCredentials: true }).then((resp) => {
            alert(resp.data)
        }).catch((err) => {
            alert(err.data)
        })
    }
    function returnPord(e) {
        e.preventDefault()
        if (isValidate()) {
            axios.post("http://localhost:8080/profile/returnproducts", { rating, otp }, { withCredentials: true }).then((resp) => {
                alert(resp.data)
                if (resp.data != "Sorry but OTP does not match")
                    navigate("/profile")
            }).catch((err) => {
                alert(err.data)
            })
        }
    }
    function isValidate() {
        if (!(parseInt(rating) <= 5 && parseInt(rating) >= 1))
            setErrRating("Rating must be between 1 to 5")
        else if (otp.length != 6){
            setErrRating("")
            setErrOtp("Please Enter Valid Otp")
        }
        else {
            setErrOtp("")
            return true
        }
        return false
    }
    return (
        <div className="container my-5">
            <div className="tab-pane fade show active row justify-content-center" id="pills-login" role="tabpanel" aria-labelledby="tab-login" >
                <form onSubmit={returnPord} >
                    <div className="text-center mb-3">
                        <h3>Return Porduct</h3>
                    </div>

                    <div className="form-outline mt-4">
                        <input type="number" id="rating" className="form-control" required value={rating} onChange={(e) => setRating(e.target.value)} />
                        <label className="form-label" htmlFor="rating">Rating(From 1 to 5)</label>
                        <Notch />
                    </div>
                    <span className="text-danger">{errrating}</span>

                    <div className="form-outline mt-4">
                        <input type="number" id="otp" className="form-control" required value={otp} onChange={(e) => setOtp(e.target.value)} />
                        <label className="form-label" htmlFor="otp">OTP</label>
                        <Notch />
                    </div>
                    <span className="text-danger">{errotp}</span>

                    <button type="submit" className="btn btn-primary btn-block mt-4">submit</button>
                    <button type="button" onClick={generateOTP} className="btn btn-danger btn-block mt-4">Generate OTP</button>

                </form>
            </div>
        </div>
    )
}