import "./App.css";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import OrderForm from "./components/OrderForm";
import OrderList from "./components/OrderList";

function App() {
  return (
    <Router>
      <div className="container mt-4">
        <nav className="mb-4">
          <Link to="/" className="btn btn-primary me-2">
            Place Order
          </Link>
          <Link to="/orders" className="btn btn-secondary">
            View Orders
          </Link>
        </nav>

        <Routes>
          <Route path="/" element={<OrderForm />} />
          <Route path="/orders" element={<OrderList />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
