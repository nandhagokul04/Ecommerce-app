import React from "react"
import '../styles/MerchantHomecon.css'
import { RxLapTimer } from "react-icons/rx";
import { MdCurrencyExchange } from "react-icons/md";
import { MdOutlineMonitor } from "react-icons/md";
import { FaShareSquare } from "react-icons/fa";
import homeimg from '../styles/images/merchome.png'
import { MdOutlineProductionQuantityLimits } from "react-icons/md";

function MerchantHomecon() {
    return (
        <div className="mhc">
            <div className="mer-img">
                <div className="image">
                <img src={homeimg} alt="" /></div>
                <div className="con">
                <h1>welcome to the merchant hub <MdOutlineProductionQuantityLimits id="hub"/> </h1>
                <p>Welcome aboard! We're thrilled to have you join our community of sellers. Get ready to showcase your products to millions of potential customers and grow your business with us. Our platform offers seamless integration, powerful tools for managing your inventory, and dedicated support to help you succeed. Let's embark on this journey together and unlock endless opportunities for growth. </p>
                </div>
            </div>
            <div className="cont">
                <div className="d1">
                <MdCurrencyExchange  id="img1" />
                    <h4>Effortless Payments</h4>
                    <p>Take care of business quickly and easily with custom reports at your fingertips.</p>
                </div>
                <div className="d2">
                <MdOutlineMonitor  id="img2"/>
                    <h4>Streamline Disputes</h4>
                    <p>Resolve disputes quickly, get details and custom alerts; manage responses, and more, all in one place.</p>
                </div>
                <div className="d3">
                <FaShareSquare id="img3"/>
                    <h4>Signs & Supplies</h4>
                    <p>Attract customers with complimentary signage - everything from stickers to logos, one click away.</p>
                </div>
                <div className="d4">
                <RxLapTimer id="img4" />
                    <h4>24/7 Access</h4>
                    <p>Ask questions and submit requests, 24/7, plus take advantage of interactive help and live chat. </p>
                </div>
            </div>
        </div>
    )

}
export default MerchantHomecon