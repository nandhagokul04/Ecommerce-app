import React from "react"
import UserNavbar from "./UserNavbar"
import ViewProducts from "./ViewProducts"
import { Routes, Route } from "react-router-dom";
import UserAddress from "./usercomponents/UserAddress";
import UserAccount from "./usercomponents/UserAccount";
import UserOrder from "./usercomponents/UserOrder";
import UserCart from "./usercomponents/UserCart";

function UserHome() {
    return (
        <div className="userhome">
            <div className="navbar">
                <UserNavbar/>
            </div>
            <Routes>
                <Route path="/viewproducts" element={<ViewProducts/>} />
                <Route path="/useraddress" element={<UserAddress/>} />
                <Route path="/useraccount" element={<UserAccount/>} />
                <Route path="/viewcart" element={<UserCart/>} />
                <Route path="/orders" element={<UserOrder/>} />

            </Routes>
        </div>
    )
}
export default UserHome