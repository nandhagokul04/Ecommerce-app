import React, { useState } from "react";
import ViewProducts from "./ViewProducts";
import '../styles/UserNavbar.css';
import logo from '../styles/images/user-navbar-logo.png';
import { RiAccountPinBoxFill } from "react-icons/ri";
import { FaOpencart } from "react-icons/fa";
import { FaAddressBook } from "react-icons/fa6";
import { TbHandClick } from "react-icons/tb";
import { MdLocalPrintshop } from "react-icons/md";
import { FaHome } from "react-icons/fa";
import { Link } from "react-router-dom";

function UserNavbar() {
    const [home, setHome] = useState(true);

    function hideHome() {
        setHome(false);
    }

    function showHome() {
        setHome(true);
    }

    return (
        <div className="usernavbar">
            <div className="user-navbar-links">
                <img id="logo" src={logo} alt="" />
                <Link to="/userhome/viewproducts" onClick={showHome} > <FaHome id="home_img" />   Home </Link> <br />
                <Link to="/userhome/viewcart" onClick={hideHome}><FaOpencart id="cart_img" />  Cart </Link> <br />
                <Link to="/userhome/orders" onClick={hideHome}><MdLocalPrintshop id="order_img" />  Orders </Link> <br />
                <Link to="/userhome/useraddress" onClick={hideHome}><FaAddressBook id="address_img" />  Address </Link> <br />
                <Link to="/userhome/useraccount" onClick={hideHome}><RiAccountPinBoxFill id="account_img" />  Account </Link> <br />
                <Link to="/" onClick={hideHome}>Logout  <TbHandClick id="logout_img" /></Link> <br />
                <input type="search" placeholder="search catagory -)" />
            </div>
            <div className="user-navbar-home">
                {home ? <ViewProducts /> : null}
            </div>
        </div>
    );
}

export default UserNavbar;
