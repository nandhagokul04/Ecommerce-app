import Form from 'react-bootstrap/Form';
import "../styles/Login.css"
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios'
import image from '../styles/images/merchantlin.jpg'

const MerchantLogin = () => {
  const [email, setemail] = useState("")
  const [password, setpassword] = useState("")
  let navigate = useNavigate();

  let submit = (e) => {
    e.preventDefault()
    console.log("submit fun");
    axios.post(`http://localhost:8080/merchants/verify?email=${email}&&password=${password}`)
      .then((res) => {
        console.log(res.data);
        localStorage.setItem("Merchant", JSON.stringify(res.data))
        navigate("/merchanthome")
      })
      .catch(() => {
        alert("Invalid user or password 😵")
      })
  }

  return (
    <div className="merchantlogin">
      <div className="merchantlinimg">
        <img src={image} alt="" />
      </div>

      <div className="merchantlogincontent">
        <h3>Merchant Login</h3>
        <form onSubmit={submit}>
          <input id='email' value={email} onChange={(e) => { setemail(e.target.value) }} type="email" placeholder="Enter email" /> <br />
          <input id='pass' value={password} onChange={(e) => { setpassword(e.target.value) }} type="password" placeholder="Password" /> <br />
          <button id='signinbtn'> sign in</button>
          <hr  id='hr'/>
          <div className="sup">
            <p>New User ? <span> <a href={'/merchantsignup'}>sign up </a></span></p>
            </div>
        </form>
      </div>
    </div>
  )
}
export default MerchantLogin