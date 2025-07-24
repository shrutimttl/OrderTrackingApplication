import logo from "./logo.svg";
import "./App.css";
import OrderForm from "./components/OrderForm";
import OrderList from "./components/OrderList";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Order Tracking System</h1>
      </header>
      <OrderForm />
      <OrderList />
    </div>
  );
}

export default App;
