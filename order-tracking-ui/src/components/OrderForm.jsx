import React, { useState } from "react";
import axios from "axios";

const OrderForm = () => {
  const [productName, setProductName] = useState("");
  const [quantity, setQuantity] = useState("");
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);
  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage(null);
    setError(null);
    try {
      const response = await axios.post("http://localhost:9091/order/", {
        productName,
        quantity: parseInt(quantity),
      });
      setMessage(response.data);
      setProductName("");
      setQuantity("");
    } catch (err) {
      setError(err.response?.data || "Failed to place order");
    }
  };
  return (
    <div className="container mt-5">
      <h3>Place a New Order</h3>
      <form onSubmit={handleSubmit} className="mt-4">
        <div className="mb-3">
          <label className="form-label">Product Name</label>
          <input
            type="text"
            className="form-control"
            value={productName}
            onChange={(e) => setProductName(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Quantity</label>
          <input
            type="number"
            className="form-control"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            required
            min="1"
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Place Order
        </button>
      </form>

      {message && <div className="alert alert-success mt-3">{message}</div>}
      {error && <div className="alert alert-danger mt-3">{error}</div>}
    </div>
  );
};

export default OrderForm;
