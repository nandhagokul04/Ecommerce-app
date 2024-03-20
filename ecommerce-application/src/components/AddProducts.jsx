import { useState } from "react"
import axios from "axios"
import '../styles/UpdateMerchant.css'

function AddProducts(){
    let [name, setname] = useState("")
    let [brand, setbrand] = useState("")
    let [catagory, setcatagory] = useState("")
    let [description, setdescription] = useState("")
    let [image_url, setimage_url] = useState("")
    let [cost, setcost] = useState("")
    let data = {name, brand,catagory,description,image_url,cost }
    let localdata=JSON.parse(localStorage.getItem("Merchant"))
    let merchant_id=localdata.data.id;
    let addmerchant = (e) => {
        e.preventDefault();
        console.log(data);
        axios.post(`http://localhost:8080/products/${merchant_id}`,data)
            .then((res) => {
                console.log(res);
                alert("product added successfully")
            })
            .catch(() => {
                alert("product adding failed !")
                console.log(merchant_id);
            })
    }
    return(
        <div className="addproducts">
             <div className="mform">
                <form onSubmit={addmerchant} action="">
                    <div className="name">
                        {/* <label htmlFor="">Name</label> */}
                        <input value={name} onChange={(e) => { setname(e.target.value) }} type="text" placeholder="Enter Name" required />
                    </div>
                    <div className="id">
                        {/* <label htmlFor="">Id</label> */}
                        <input value={brand} onChange={(e) => { setbrand(e.target.value) }} type="text" placeholder="Enter Brand" required />
                    </div>
                    <div className="gst">
                        {/* <label htmlFor="">gst_number</label> */}
                        <input type="text" value={catagory} onChange={(e) => { setcatagory(e.target.value) }} placeholder="Enter Catagory" required />
                    </div>
                    <div className="email">
                        {/* <label htmlFor="">Email</label> */}
                        <input type="text" value={description} onChange={(e) => { setdescription(e.target.value) }} placeholder="Enter Description" required />
                    </div>
                    <div className="phone">
                        {/* <label htmlFor="">Phone</label> */}
                        <input type="text" value={image_url} onChange={(e) => { setimage_url(e.target.value) }} placeholder="Enter Image url" required />
                    </div>
                    <div className="pass">
                        {/* <label htmlFor="">Password</label> */}
                        <input type="text" value={cost} onChange={(e) => { setcost(e.target.value) }} placeholder="Enter cost" required />
                    </div>
                    <button className='btn btn-outline-danger'>Submit</button>
                </form>
            </div>
        </div>
    )
}
export default AddProducts