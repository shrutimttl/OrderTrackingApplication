import { useState } from "react";
import axios from "../axiosConfig";
import { useNavigate } from "react-router-dom";

const LoginForm = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [token, setToken] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    localStorage.removeItem("token");
    try {
      const response = await axios.post("/auth/login", {
        username,
        password,
      });
      console.log("Login successful:");
      setToken(response.data);
      console.log(response.data);
      localStorage.setItem("token", response.data);
      navigate("/dashboard");
    } catch (err) {
      alert(username);
    }
  };

  return (
    <form onSubmit={handleLogin}>
      <input
        type="text"
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Username"
      />
      <input
        type="password"
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Password"
      />
      <button type="submit">Login</button>
    </form>
  );
};

export default LoginForm;
