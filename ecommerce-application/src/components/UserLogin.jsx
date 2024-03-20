import Form from 'react-bootstrap/Form';
import "../styles/Login.css";
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';
import image from '../styles/images/signup.avif'


const UserLogin = () => {
  const [email, setemail] = useState("")
  const [password, setpassword] = useState("")
  let navigate = useNavigate();

 
  let submit = (e) => {
    e.preventDefault()
    console.log("submit fun");
    axios.post(`http://localhost:8080/users/verify?email=${email}&&password=${password}`)
      .then((res) => {
        console.log(res);
        navigate("/userhome")
      })
      .catch((res) => {
        alert("Invalid user or password 😵‍💫")
        console.log(res);

      })

  }

  return (
    <div className="merchantlogin">
      <div className="userloginimage">
        <img src={image} alt="aaaimg" />
      </div>
      <div className="userlogincontent">
        <h3 >Userlogin</h3>
        <form onSubmit={submit}> 
          <input id='email' value={email} onChange={(e) => { setemail(e.target.value) }} type="email" placeholder="Enter email" /> <br />
          <input  id='pass' value={password} onChange={(e) => { setpassword(e.target.value) }} type="password" placeholder="Password" /> <br />
          <button id='signinbtn'> sign in</button> <br />
          <hr  id='hr'/>
          <div className="sup">
            <p>New User ? <span> <a href={'/usersignup'}>sign up </a></span></p>
            </div>
        </form>
      </div>
    </div>
  )
}
export default UserLogin