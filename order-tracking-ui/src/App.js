import "./App.css";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import Dashboard from "./components/Dashboard";
import LoginForm from "./components/LoginForm";
import { useState, useEffect } from "react";

function App() {
  const [token, setToken] = useState(localStorage.getItem("token"));

  useEffect(() => {
    setToken(localStorage.getItem("token")); // refresh token on load
  }, []);

  return (
    <Routes>
      <Route
        path="/"
        element={
          token ? <Navigate to="/dashboard" /> : <Navigate to="/login" />
        }
      />
      <Route path="/login" element={<LoginForm />} />
      <Route
        path="/dashboard/*"
        element={token ? <Dashboard /> : <Navigate to="/login" />}
      />
      {/* Add other protected routes like this */}
    </Routes>
  );
}

export default App;
