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
                <Link to="/userhome/viewproducts" onClick={showHome} > <FaHome /> Home</Link> <br />
                <Link to="/userhome/viewcart" onClick={hideHome}><FaOpencart /> Cart</Link> <br />
                <Link to="/userhome/useraccount" onClick={hideHome}><RiAccountPinBoxFill /> Account</Link> <br />
                <Link to="/userhome/orders" onClick={hideHome}><MdLocalPrintshop /> Orders</Link> <br />
                <Link to="/userhome/useraddress" onClick={hideHome}><FaAddressBook /> Address</Link> <br />
                <input type="search"  placeholder="search product🔎"/>
                <Link to="/" onClick={hideHome}>Logout <TbHandClick /></Link> <br />            </div>
            <div className="user-navbar-home">
               {home ? <ViewProducts /> : null}
            </div>
        </div>
    );
}

export default UserNavbar;
