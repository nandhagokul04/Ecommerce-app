
import React from "react"
import { Navigate } from "react-router"

function UserProtect({Child}) {
    let x=localStorage.getItem("Merchant")
    let verifyProtect=()=>{
        if(x==null){
            return false
        }
        else{
            return true
        }
    }
    return(
        <div className="protect">
         {verifyProtect()?<Child/>:<Navigate to={'/'}/>}
        </div>
    )   
}
export default UserProtect