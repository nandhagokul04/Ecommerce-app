import React, { useState, useEffect } from "react";
import axios from "axios";
import '../styles/UserProductView.css';

function ViewProducts() {
    const [data, setData] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/products`)
            .then(response => {
                setData(response.data.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    return (
        <div className="productview">
            <div className="img">
                <img src="https://images-eu.ssl-images-amazon.com/images/G/31/IMG20/Home/2024/Gateway/ATFGW/holiGW/SBIbankstrip/Holi_GW_Holi-shopping_PC_._CB579439902_.jpg" alt="" />
            </div>
            <div className="product-grid">
                {data.map((product, index) => (
                    <div key={product.id} className="product-item">
                        <div className="user-products-class">
                            <div className="user-product-img">
                                <img src={product.image_url} alt="" />
                            </div>
                            <div className="user-product-content">
                                <h6 id="name">name: {product.name}</h6>
                                <h6 id="brand">brand: {product.brand}</h6>
                                <h6 id="cost">$ {product.cost}</h6>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ViewProducts;
