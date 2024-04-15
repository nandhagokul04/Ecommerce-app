import axios from "axios";
import React, { useEffect, useState } from "react";
import "./userstyles/UserOrder.css"


function UserOrder() {
    const [carts, setCarts] = useState([]);
    const localData = JSON.parse(localStorage.getItem("user"));
    const user_id = localData.id;
    useEffect(() => {
        axios.get(`http://localhost:8080/orders/${user_id}`)
            .then((res) => {
                setCarts(res.data.data); // Set carts array
            })
            .catch((err) => {
                console.log("Error occurred", err);
            });
    }, []);
    function deleteorder(x){
        axios.delete(`http://localhost:8080/orders/${x.id}`)
        .then((res) => {
           alert("Order cancelled")
           window.location.reload();
        })
        .catch((err) => {
            console.log("Error occurred", err);
        });
    }
    return (
        <div className="userorder">
            {carts.map((cart, index) => (
                <div className="orders" key={index}>
                    {cart.products.map((product, pIndex) => (
                        <div className="User_order" key={pIndex}>
                            <div className="order_image">
                                <img src={product.image_url} alt={product.name} />
                            </div>
                            <div className="order_content">
                            <p>{product.brand}{product.category}</p>
                                <p>{product.name}</p>
                                <p id="order_cost">₹{product.cost}.000</p>
                            </div>
                            <div className="order_status">
                            <p>Order Status: <span id="order_status">{cart.status}</span></p>
                            </div>
                            <div className="cancel_order">
                           <button onClick={()=>{deleteorder(cart)}}>Cancel order</button>
                            </div>
                        </div>
                    ))}
                                                        <hr />

                </div>
            ))}

        </div>
    )
}
export default UserOrder 