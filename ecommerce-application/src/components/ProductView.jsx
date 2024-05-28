import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";
import '../styles/ProductView.css'
import { useNavigate } from "react-router";
import { Edit, Search, Trash2 } from "react-feather";

function ProductView() {
    let[brand,setbrand]=useState("")
    let[category,setcategory]=useState("")
    const [data, setData] = useState([]);
    let nav = useNavigate()
    let localdata = JSON.parse(localStorage.getItem("Merchant"))
    let id = localdata.data.id;
    let[refdata,setrefdata]=useState("")

    useEffect(() => {
        console.log("product view",id);
        axios.get(`http://localhost:8080/products/merchant_id/${id}`)
            .then(response => {
                setData(response.data.data);
                setrefdata(response.data.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
           
    }, []);
    function ref(){
        setData(refdata)
    }
    function edit(product) {
        console.log("edit called");
        nav(`/merchanthome/editproducts`, { state: product  });
    }
    function deleteproduct(id) {
        axios.delete(`http://localhost:8080/products/${id}`)
            .then(response => {
                alert("Product deleted")
            })
            .catch(error => {
                console.error('Error fetching data:', error);
                alert("Product deletion failed!")
                console.log("id:", id);

            });
    }
    function searchbrand(){
        axios.get(`http://localhost:8080/products/brand/${brand}`)
        .then(res=>{
            setData(res.data.data); 
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    }
    const searchcategory=()=>{
        axios.get(`http://localhost:8080/products/catagory/${category}`)
        .then(res=>{
            setData(res.data.data); 
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    }
    return (
        <div className="productview">
                   <div className="customnav">
                <input type="search" value={brand} placeholder="search by brand" id="search-brand" onChange={(e)=>{setbrand(e.target.value)}}/>
                <button id="brand-btn" onClick={()=>{searchbrand()}} ><Search/></button>
                {/* <input type="search" value={brand} placeholder="search by name" id="search-brand" onChange={(e)=>{setbrand(e.target.value)}}/>
                <button id="brand-btn" onClick={()=>{searchbrand()}} ><Search/></button> */}
                <input type="search" value={category} placeholder="search by catagory "id="search-catagory" onChange={(e)=>{setcategory(e.target.value)}}/>
                <button id="catagory-btn" onClick={()=>{searchcategory()}} ><Search/></button>
                <button id="refresh" onClick={ref}>Refresh </button>
                
            </div>
            <div className="div">
            {data.map((x) => (
                <li key={x.id}>
                    <div className="merchant-data">
                        <div className="merchantdata">
                            <div className="productimg">
                                <img src={x.image_url} alt="" />
                            </div>
                            <div className="content">
                                <h6 >id: {x.id}</h6>
                                <h6 id="name" >name: {x.name}</h6>
                                <h6 id="brand" >brand: {x.brand}</h6>
                                <h6 id="catagory"  >catagory: {x.catagory}</h6>
                                <h6 id="description"  >description: {x.description}</h6>
                                <h6 id="cost"  >cost: {x.cost}</h6>
                            </div>
                        </div>
                        <div className="editordelete">
                            <button id="editproduct" onClick={() => edit(x)} ><Edit />  </button>
                            <br />
                            <button id="deleteproduct" onClick={() => deleteproduct(x.id)} ><Trash2 />  </button>
                        </div>
                    </div>
                </li>

            ))}

        </div>
        </div>
    );
}
export default ProductView
