import React, { useState } from "react";
import axios from "axios";

const ModifyForm = () => {
  const [id, setId] = useState("");
  const [productName, setProductName] = useState("");
  const [quantity, setQuantity] = useState("");
  const [status, setStatus] = useState("");
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);

  const handleInputChange = (e) => {
    setId(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage(null);
    setError(null);
    try {
      const response = await axios.patch(`http://localhost:9091/order/${id}`, {
        productName,
        quantity: parseInt(quantity),
        status,
      });
      console.log("Order modified successfully:", response.data);
      setMessage(response.data);
      setId("");
      setProductName("");
      setQuantity("");
      setStatus("");
    } catch (err) {
      setError(err.response?.data || "failed to modify order");
    }
  };
  return (
    <div className="container mt-5">
      <h3>Modify an Existing Order</h3>
      <form onSubmit={handleSubmit} className="mt-4">
        <div className="mb-3">
          <label className="form-label">ID</label>
          <input
            type="number"
            className="form-control"
            value={id}
            onChange={handleInputChange}
            required
            min="1"
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Product Name</label>
          <input
            type="text"
            className="form-control"
            value={productName}
            onChange={(e) => setProductName(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Quantity</label>
          <input
            type="number"
            className="form-control"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            min="1"
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Status</label>
          <input
            type="text"
            className="form-control"
            value={status}
            onChange={(e) => setStatus(e.target.value)}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Modify Order
        </button>
      </form>

      {message && <div className="alert alert-success mt-3">{message}</div>}
      {error && <div className="alert alert-danger mt-3">{error}</div>}
    </div>
  );
};
export default ModifyForm;
