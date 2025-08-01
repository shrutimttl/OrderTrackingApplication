import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const LogoutForm = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const logoutWithConfirmation = () => {
      localStorage.removeItem("token");
      navigate("/login");
    };

    logoutWithConfirmation();
  }, [navigate]);

  return null;
};

export default LogoutForm;
