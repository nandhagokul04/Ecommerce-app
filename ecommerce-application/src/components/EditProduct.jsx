import { useState } from "react"
import axios from "axios"
import { useLocation } from "react-router"

function EditProduct() {
    console.log("edit product page");
    const location = useLocation();
    const product = location.state||{};
    console.log("product",product);

    let [id, setid] = useState(product.id || "");
    let [name, setname] = useState(product.name || "");
    let [brand, setbrand] = useState(product.brand || "");
    let [catagory, setcatagory] = useState(product.catagory || "")
    let [description, setdescription] = useState(product.description || "")
    let [image_url, setimage_url] = useState(product.image_url || "")
    let [cost, setcost] = useState(product.cost || "")

    let localdata = JSON.parse(localStorage.getItem("Merchant"))
    let merchant_id = localdata.data.id;

    let data = { id,name, brand, catagory, description, image_url, cost }

   
    let addmerchant = (e) => {
        e.preventDefault();
        console.log(data);
        axios.put(`http://localhost:8080/products`, data)
            .then((res) => {
                console.log(res);
                alert("product updated successfully")
            })
            .catch(() => {
                alert("product updation failed !")
                console.log(merchant_id);
            })
    }
    return (
        <div className="addproducts">
            <div className="mform">
                <form onSubmit={addmerchant} action="">
                    <div className="id">
                        {/* <label htmlFor="">Name</label> */}
                        <input value={id} onChange={(e) => { setid(e.target.value) }} type="text" placeholder="Enter id" required />
                    </div>
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
export default EditProduct