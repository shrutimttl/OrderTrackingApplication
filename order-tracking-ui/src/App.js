import logo from "./logo.svg";
import "./App.css";
import OrderForm from "./components/OrderForm";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Order Tracking System</h1>
      </header>
      <OrderForm />
    </div>
  );
}

export default App;
