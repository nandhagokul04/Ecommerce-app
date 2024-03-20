import { Routes, Route } from "react-router-dom";
import MerchantNavbar from "./MerchantNavbar";
import ProductView from "./ProductView";
import UpdateMerchant from "./UpdateMerchant";
import MerchantHomecon from "./MerchantHomecon";
import '../styles/MerchantNavbar.css';
import AddProducts from "./AddProducts";
import EditProduct from "./EditProduct";


function MerchantHome() {
  return (
    <div className="merchanthome">
      <MerchantNavbar />
        <Routes>
          <Route path="/productview" element={<ProductView />} /> 
          <Route path="/updatemerchant" element={<UpdateMerchant />} /> 
          <Route path="/merchanthomecon" element={<MerchantHomecon />} /> 
          <Route path="/updatemerchant" element={<UpdateMerchant />} /> 
          <Route path="/addproducts" element={<AddProducts />} /> 
          <Route path="/editproducts" element={<EditProduct />} /> 
        </Routes>
        

    </div>
  );
}

export default MerchantHome;
