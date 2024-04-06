
import React from "react"
import { Navigate } from "react-router"

function ProtectUser({Child}) {
    let x=localStorage.getItem("user")
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
export default ProtectUser