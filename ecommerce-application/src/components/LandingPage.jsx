import React, { useState } from 'react';
import '../styles/Landingpage.css';
import { useNavigate } from 'react-router';
import { FaSackDollar } from "react-icons/fa6";
import { ShoppingBag } from 'react-feather';



const LandingPage = () => {

    let nav = useNavigate()
    const userpage = () => {
        nav('/userlogin')
    }
    const merchantpage = () => {
        nav('/merchantlogin')
    }

    const aaaa = () => {
        nav("/merchanthome")
    }
    const user = () => {
        nav("/userhome")
    }

    return (
        <div className="landingpage">
            <div className="mer-cont">
                <div className="joinus">
                    <p id='joinus'>Shop with Us!</p>
                    <p id='para'>Welcome to Merchant! Discover quality products and enjoy a seamless shopping experience with us. Happy browsing!</p>
                </div>
                {/* <div className="landing-image">
                    <img src="https://img.freepik.com/free-vector/seasonal-sale-discounts-presents-purchase-visiting-boutiques-luxury-shopping-price-reduction-promotional-coupons-special-holiday-offers-vector-isolated-concept-metaphor-illustration_335657-2766.jpg?w=740" alt="" />
                </div> */}
                <button id='individualbtn' onClick={userpage}>
                    <ShoppingBag id='usericon' />  user -{'>'}
                </button>
            </div>
            <div className="lpcontent">
                <div className="joinus">
                    <p id='buisnessp'>Buisness with Us!</p>
                    <p id='para'>Welcome back! Your journey continues with us. Log in to unlock exclusive offers and personalized experiences. Happy exploring!</p>
                </div>
                {/* <div className="landing-image">
                    <img src="https://img.freepik.com/free-vector/seasonal-sale-discounts-presents-purchase-visiting-boutiques-luxury-shopping-price-reduction-promotional-coupons-special-holiday-offers-vector-isolated-concept-metaphor-illustration_335657-2766.jpg?w=740" alt="" />
                </div> */}
                <button id='buisness' onClick={merchantpage}>
                    <FaSackDollar id='merchanticon' />
                     merchant  -{'>'}
                </button>
            </div>
            <button onClick={aaaa}>aaa</button>
            <button onClick={user}>user</button>
        </div>
    );
};

export default LandingPage;
