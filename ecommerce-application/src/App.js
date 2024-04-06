import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./components/LandingPage";
import MerchantLogin from "./components/MerchantLogin";
import UserLogin from "./components/UserLogin";
import MerchantSignUp from "./components/MerchantSignUp";
import 'bootstrap/dist/css/bootstrap.min.css';
import MerchantHome from "./components/MerchantHome";
import UserHome from "./components/UserHome";
import UserSignUp from "./components/UserSignUp";
import Error from "./components/Error";
import Protect from "./components/Protect";
import ProtectUser from "./components/ProtectUser";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<LandingPage />} />
          <Route path='/*' element={<Error />} />
          <Route path='/merchantlogin' element={<MerchantLogin />} />
          <Route path='/userlogin' element={<UserLogin />} />
          <Route path='/merchantsignup' element={<MerchantSignUp />} />
          <Route path='/usersignup' element={<UserSignUp />} />
          <Route path='/merchanthome/*' element={<Protect Child={MerchantHome}/>} />
          <Route path='/userhome/*' element={<ProtectUser Child={UserHome}/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
