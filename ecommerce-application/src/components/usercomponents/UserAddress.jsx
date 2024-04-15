import axios from "axios";
import { useState, useEffect } from "react";
import './userstyles/UserAddress.css'
import { Edit } from "react-feather";

function UserAddress() {
    // view address
    const [data, setData] = useState([]);
    const localdata = JSON.parse(localStorage.getItem("user"));
    const uid = localdata.id;

    // save address
    const [type, settype] = useState("");
    const [buildingname, setbuildingname] = useState("");
    const [landmark, setlandmark] = useState("");
    const [area, setarea] = useState("");
    const [city, setcity] = useState("");
    const [state, setstate] = useState("");
    const [country, setcountry] = useState("");
    const [pincode, setpincode] = useState("");
    let address = { type, buildingname, landmark, area, city, state, country, pincode }

    // Update address
    const[id,setid]=useState("")


    useEffect(() => {
        axios.get(`http://localhost:8080/address/user_id/${uid}`)
            .then(response => {
                setData(response.data.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    function saveaddress() {
        console.log("use eff address",uid);
        axios.post(`http://localhost:8080/address/${uid}`, address)
            .then(() => {
                alert("Address saved")
            })
            .catch(error => {
                alert("Address saving failed")
                console.error('Error fetching data:', error);
            });
    }
    function setEditAddressFields(x) {
        setid(x.id);
        settype(x.type);
        setbuildingname(x.buildingname);
        setlandmark(x.landmark);
        setarea(x.area);
        setcity(x.city);
        setstate(x.state);
        setcountry(x.country);
        setpincode(x.pincode);
    }

    function editaddress() {
        let edaddress = { id,type, buildingname, landmark, area, city, state, country, pincode }
        axios.put(`http://localhost:8080/address`, edaddress)
            .then(() => {
                alert("Address updated");
            })
            .catch(error => {
                alert("Address updating failed");
                console.error('Error fetching data:', error);
            });
    }


   

    return (
        <div className="useraddress">
            <div className="my-address">
                <div className="addresses">
                    {data.map((x, index) => (
                        <div key={index}>
                            <p>type: {x.type}</p>
                            <p>buildingname: {x.buildingname}</p>
                            <p>landmark: {x.landmark}</p>
                            <p>area: {x.area}</p>
                            <p>city: {x.city}</p>
                            <p>state: {x.state}</p>
                            <p>country: {x.country}</p>
                            <p>pincode: {x.pincode}</p>
                            <button onClick={() => setEditAddressFields(x)}><Edit/></button>
                            <hr />
                        </div>

                    ))}
                </div>
            </div>
            <div className="add-address">
                <h4>Add Address</h4>
                <input type="text" placeholder="type" onChange={((e) => { settype(e.target.value) })} /> <br />
                <input type="text" placeholder="building name" onChange={((e) => { setbuildingname(e.target.value) })} /> <br />
                <input type="text" placeholder="landmark" onChange={((e) => { setlandmark(e.target.value) })} /> <br />
                <input type="text" placeholder="area" onChange={((e) => { setarea(e.target.value) })} /> <br />
                <input type="text" placeholder="city" onChange={((e) => { setcity(e.target.value) })} /> <br />
                <input type="text" placeholder="state" onChange={((e) => { setstate(e.target.value) })} /> <br />
                <input type="text" placeholder="country" onChange={((e) => { setcountry(e.target.value) })} /> <br />
                <input type="text" placeholder="pincode" onChange={((e) => { setpincode(e.target.value) })} /> <br />
                <button onClick={saveaddress}>save address</button>
            </div>
            <div className="add-address">
                <h4>Edit Address</h4>
                <input type="text" value={type} placeholder="type" onChange={(e) => settype(e.target.value)} /> <br />
                <input type="text" value={buildingname} placeholder="building name" onChange={(e) => setbuildingname(e.target.value)} /> <br />
                <input type="text" value={landmark} placeholder="landmark" onChange={(e) => setlandmark(e.target.value)} /> <br />
                <input type="text" value={area} placeholder="area" onChange={(e) => setarea(e.target.value)} /> <br />
                <input type="text" value={city} placeholder="city" onChange={(e) => setcity(e.target.value)} /> <br />
                <input type="text" value={state} placeholder="state" onChange={(e) => setstate(e.target.value)} /> <br />
                <input type="text" value={country} placeholder="country" onChange={(e) => setcountry(e.target.value)} /> <br />
                <input type="text" value={pincode} placeholder="pincode" onChange={(e) => setpincode(e.target.value)} /> <br />

                <button onClick={editaddress}>update address</button>
            </div>

        </div>
    );
}

export default UserAddress;
