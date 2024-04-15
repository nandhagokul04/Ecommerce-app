import axios from "axios"
import { useEffect, useState } from "react"
import "../usercomponents/userstyles/UserAccount.css"
import { Edit } from "react-feather";

function UserAccount(){
    const localdata = JSON.parse(localStorage.getItem("user"));
    const id = localdata.id;
    const[data,setData]=useState("")
    useEffect(()=>{
        axios.get(`http://localhost:8080/users/get/${id}`)
        .then(response => {
            setData(response.data.data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    },[])

    // update user
    let [name, setname] = useState("")
    let [email, setemail] = useState("")
    let [phone, setphone] = useState("")
    let [password, setpassword] = useState("")

    function editacc(data){
        setname(data.name)
        setemail(data.email)
        setphone(data.phone)
        setpassword(data.password)
    }

    let updated_data={id,name,email,phone,password}
    let addmerchant = (e) => {
        e.preventDefault();
        axios.put('http://localhost:8080/users',updated_data)
        .then((res)=>{
            console.log(res);
            alert("User updated successfully")
        })
        .catch(()=>{
            alert( "User failed updating")
        })
        
    }
    return(
        <div className="useraccount">
            <div className="my-account">
               <p>UserName: {data.name}</p>
               <p>Email: {data.email}</p>
               {/* <p>Password: {data.password}</p> */}
               <p>Phone: {data.phone}</p>
               <button onClick={()=>{editacc(data)}}><Edit id="editadd"/></button>
        </div>
        <div className="edit_user__acc">
        <form onSubmit={addmerchant} action="">
                <div className="name">
                {/* <label htmlFor="">Name</label> */}
                <input value={name} onChange={(e) => { setname(e.target.value) }} type="text" placeholder="Enter Name" required />
                </div>
                {/* <div className="email">
                <input type="email" value={email} onChange={(e) => { setemail(e.target.value) }} placeholder="Enter email" required />
                </div> */}
                <div className="phone">
                {/* <label htmlFor="">Phone</label> */}
                <input type="tel" value={phone} onChange={(e) => { setphone(e.target.value) }} pattern="[0-9]{10}" placeholder="Enter phone" required />
                </div>
                <div className="pass">
                {/* <label htmlFor="">Password</label> */}
                <input type="text" value={password} onChange={(e) => { setpassword(e.target.value) }} placeholder="Enter password" required />
                </div>
                <button className='btn btn-outline-danger'>Submit</button>
            </form>
        </div>
        </div>
    )
}
export default UserAccount 