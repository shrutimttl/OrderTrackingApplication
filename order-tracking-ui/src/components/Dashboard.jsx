import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import OrderForm from "./OrderForm";
import OrderList from "./OrderList";
import ModifyForm from "./ModifyForm";
import LogoutForm from "./LogoutForm";

function Dashboard() {
  return (
    <div className="container mt-4">
      <nav className="mb-4">
        <Link to="/" className="btn btn-primary me-2">
          Place Order
        </Link>
        <Link to="/dashboard/orders" className="btn btn-secondary">
          View Orders
        </Link>
        <Link to="/dashboard/modify-orders" className="btn btn-secondary">
          Modify Order
        </Link>
        <Link to="/dashboard/logout" className="btn btn-danger ms-2">
          Logout
        </Link>
      </nav>

      <Routes>
        <Route path="/" element={<OrderForm />} />
        <Route path="/orders" element={<OrderList />} />
        <Route path="/modify-orders" element={<ModifyForm />} />
        <Route path="/logout" element={<LogoutForm />} />
        <Route path="*" element={<h2>Page Not Found</h2>} />
      </Routes>
    </div>
  );
}

export default Dashboard;
