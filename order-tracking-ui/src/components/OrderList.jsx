import { useState, useEffect } from "react";
import axios from "axios";
import { Spinner, Alert, Table } from "react-bootstrap";

const OrderList = () => {
  const [orders, setOrders] = useState("");
  const [loading, setlLoading] = useState(true);
  const [error, setError] = useState(null);
  const fetchOrders = async () => {
    try {
      const response = await axios.get("http://localhost:9091/order/");
      setOrders(response.data);
    } catch (err) {
      setError("failed to fetch orders");
    } finally {
      setlLoading(false);
    }
  };
  useEffect(() => {
    fetchOrders();
  }, []);
  if (loading) return <Spinner animation="border" variant="primary" />;
  if (error) return <Alert variant="danger">{error}</Alert>;
  return (
    <div className="container mt-4">
      <h2>All Orders</h2>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Customer</th>
            <th>Product</th>
            <th>Status</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {orders.length > 0 ? (
            orders.map((order, index) => (
              <tr key={order.id || index}>
                <td>{order.id}</td>
                <td>{order.customerName}</td>
                <td>{order.productName}</td>
                <td>{order.status}</td>
                <td>{order.orderDate}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5" className="text-center">
                No orders found.
              </td>
            </tr>
          )}
        </tbody>
      </Table>
    </div>
  );
};

export default OrderList;
