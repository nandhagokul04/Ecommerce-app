import axios from "axios";
import React, { useEffect, useState } from "react";
import './userstyles/UserCart.css'
import { useNavigate } from "react-router";

function UserCart() {
    const [carts, setCarts] = useState([]);
    const localData = JSON.parse(localStorage.getItem("user"));
    const user_id = localData.id;
    let nav=useNavigate()

    useEffect(() => {
        axios.get(`http://localhost:8080/cart/${user_id}`)
            .then((res) => {
                console.log("cart data", res.data.data);
                setCarts(res.data.data); // Set carts array
            })
            .catch((err) => {
                console.log("Error occurred", err);
            });
    }, []);
    function gotocart(x,id){
        axios.post(`http://localhost:8080/orders?product_id=${x.id}&user_id=${user_id}`)
        .then(response => {
           alert("order placed successfully")
           placeanddeletecart(id)
       })
       .catch(error => {
           alert("Existing order  ")
           console.error('Error fetching data:', error);
       });
       
       }
       function deletecart(x){
        axios.delete(`http://localhost:8080/cart/${x}`)
        .then(response => {
            alert("Product removed from cart")
            window.location.reload();
       })
       .catch(error => {   
           console.error('Error fetching data:', error);
       });
       
       }
       function placeanddeletecart(x){
        axios.delete(`http://localhost:8080/cart/${x}`)
        .then(response => {
           nav("/userhome/orders")
       })
       .catch(error => {   
           console.error('Error fetching data:', error);
       });
       
       }

    return (
        <div className="usercart">
            {carts.map((cart, index) => (
                <div className="cart" key={index}>
                    {cart.products.map((product, pIndex) => (
                        <div className="product_cart" key={pIndex}>
                            <div className="product_image">
                                <img src={product.image_url} alt={product.name} />
                            </div>
                            <div className="product_content">
                                <h4>{product.name}</h4>
                                <p>{product.brand}{product.category}</p>
                                <p id="cart_description">{product.description}</p>
                                <p id="cart_cost">₹{product.cost}.000</p>
                            </div>
                            <div className="place_order_btn">
                                <button onClick={()=>{gotocart(product,cart.id)}}>buy now</button>
                            </div>
                            <div className="delete_cart_btn">
                                <button onClick={()=>{deletecart(cart.id)}}>Remove</button>
                            </div>

                        </div>
                    ))}
                    <hr />
                </div>
            ))}
        </div>
    );
}

export default UserCart;
