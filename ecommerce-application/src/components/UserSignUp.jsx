import { useState } from "react"
import axios from 'axios';
import image from '../styles/images/usersignup.jpg'


function UserSignUp() {
    let [name, setname] = useState("")
    let [email, setemail] = useState("")
    let [phone, setphone] = useState("")
    let [password, setpassword] = useState("")

    let data={name,email,phone,password}

    let addmerchant = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/users',data)
        .then((res)=>{
            console.log(res);
            alert("Verification link was sent to your registered Email ID")
        })
        .catch(()=>{
            alert( "User adding failed")
        })
        
    }

    return (
        <div className="merchantsignup">
            <div className="image">
                <img src={image} alt="" />
            </div>
            <div className="form">
                <h4 id="in">user signup</h4>
            <form onSubmit={addmerchant} action="">
                <div className="name">
                {/* <label htmlFor="">Name</label> */}
                <input value={name} onChange={(e) => { setname(e.target.value) }} type="text" placeholder="Enter Name" required />
                </div>
                <div className="email">
                {/* <label htmlFor="">Email</label> */}
                <input type="email" value={email} onChange={(e) => { setemail(e.target.value) }} placeholder="Enter email" required />
                </div>
                <div className="phone">
                {/* <label htmlFor="">Phone</label> */}
                <input type="tel" value={phone} onChange={(e) => { setphone(e.target.value) }} pattern="[0-9]{10}" placeholder="Enter phone" required />
                </div>
                <div className="pass">
                {/* <label htmlFor="">Password</label> */}
                <input type="password" value={password} onChange={(e) => { setpassword(e.target.value) }} placeholder="Enter password" required />
                </div>
                <button className='btn btn-outline-danger'>Submit</button>
            </form>
            </div>
        </div>
    )
}
export default UserSignUp;