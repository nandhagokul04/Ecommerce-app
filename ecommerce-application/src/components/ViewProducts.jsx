import React, { useState, useEffect } from "react";
import axios from "axios";
import '../styles/UserProductView.css';
import {useNavigate} from 'react-router-dom'

function ViewProducts() {
    let nav=useNavigate();
    const [data, setData] = useState([]);
    let localdata=JSON.parse(localStorage.getItem("user"))
    let user_id=localdata.id

    useEffect(() => {
        axios.get(`http://localhost:8080/products`)
            .then(response => {
                setData(response.data.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);
    function gotocart(x){
     axios.post(`http://localhost:8080/cart?product_id=${x.id}&user_id=${user_id}`)
     .then(response => {
        alert("Successfully added to cart")
        nav("/userhome/viewcart")
    })
    .catch(error => {
        alert("Product Already added")
        console.error('Error fetching data:', error);
    });
    }

    return (
        <div className="productview">
            <div className="img">
                <img src="https://images-eu.ssl-images-amazon.com/images/G/31/IMG20/Home/2024/Gateway/ATFGW/holiGW/SBIbankstrip/Holi_GW_Holi-shopping_PC_._CB579439902_.jpg" alt="" />
            </div>
            <div className="product-grid">
                {data.map((product, index) => (
                    <div key={product.id} className="product-item">
                        
                        <div className="user-products-class">
                        <h6 id="name">{product.name}</h6>
                        </div>
                            <div className="user-product-img">
                                <img src={product.image_url} alt="" />
                            </div>
                            <div className="user-product-content">
                               
                                <h6 id="desc">{product.description}</h6>
                                <h6 id="cost">$ {product.cost}.00</h6>
                                <button onClick={()=>{gotocart(product)}}>Add To Cart</button>
                            
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ViewProducts;
