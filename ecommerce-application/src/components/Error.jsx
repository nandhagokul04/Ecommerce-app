import React from "react"
import err from "../styles/images/err.jpg"

function Error(){
    return(
    <div className="error">
            <img style={{ width: "100vh" ,height: "100vh",margin:"0px 0px 0px 400px" }} src={err} alt="" />
    </div>
    )
}
export default Error