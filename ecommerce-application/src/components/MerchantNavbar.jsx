import React, { useState } from "react";
import { LogOut, ShoppingCart } from "react-feather";
import { Link} from "react-router-dom";
import '../styles/MerchantNavbar.css';
import MerchantHomecon from "./MerchantHomecon";
import { BiSolidShoppingBags } from "react-icons/bi";
import { MdAccountCircle } from "react-icons/md";

function MerchantNavbar() {
    const [home, sethome] = useState(true)

    function hideHome() {
        console.log('hidehome called');
        sethome(false)
    }

    return (
        <div className="merchantnavbar">
            <div className="nav">
                <div className="logo">
                    <BiSolidShoppingBags id="img" />
                </div>
                <div className="homem">
                    <Link to="/merchanthome/merchanthomecon" onClick={hideHome}> <ShoppingCart /> Home</Link>
                </div>

                <div className="viewp">
                    <Link to="/merchanthome/productview" onClick={hideHome}> <ShoppingCart /> Your products</Link>
                </div>
                <div className="addp">
                    <Link to="/merchanthome/addproducts" onClick={hideHome}> <ShoppingCart /> Add product</Link>
                </div>
                {/* <div className="updatep">
                    <Link to="/productview" onClick={hideHome}> <ShoppingCart /> update product</Link>
                </div> */}
                <div className="account">
                <Link to='/merchanthome/updatemerchant' onClick={hideHome}><MdAccountCircle id="account"/> Edit Account</Link>
                </div>
                <div className="logout">
                <Link to="/"><LogOut id="logout"/> Log out</Link>
                </div>
            </div>
            <div className="homepage">
                {home ? <MerchantHomecon /> : null}
            </div>
        </div>
    );
}

export default MerchantNavbar;

