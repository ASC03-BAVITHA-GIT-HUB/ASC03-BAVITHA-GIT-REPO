import { Routes, Route, Navigate, Link, useLocation } from "react-router-dom";
import Auth from "./components/Auth.jsx";
import Dashboard from "./components/Dashboard.jsx";
import Hospitals from "./components/Hospitals.jsx";
import Doctors from "./components/Doctors.jsx";
import Patients from "./components/Patients.jsx";
import Appointments from "./components/Appointments.jsx";
import Reviews from "./components/Reviews.jsx";

function isAuthed() {
  return localStorage.getItem("auth") === "1";
}

function Protected({ children }) {
  const loc = useLocation();
  if (!isAuthed()) return <Navigate to="/" replace state={{ from: loc }} />;
  return children;
}

function TopBar() {
  const logout = () => {
    localStorage.removeItem("auth");
    localStorage.removeItem("userEmail");
    window.location.href = "/";
  };
  return (
    // in App.jsx (TopBar)
<div className="topbar">
  <div className="brand">Healthcare Management System</div>
  <div className="actions">
    <button className="btn" onClick={logout}>Logout</button>
  </div>
</div>

  );
}

export default function App() {
  return (
    <>
      <TopBar />
      <Routes>
        <Route path="/" element={<Auth />} />
        <Route
          path="/dashboard"
          element={
            <Protected>
              <Dashboard />
            </Protected>
          }
        />
        <Route
          path="/hospitals"
          element={
            <Protected>
              <Hospitals />
            </Protected>
          }
        />
        <Route
          path="/doctors"
          element={
            <Protected>
              <Doctors />
            </Protected>
          }
        />
        <Route
          path="/patients"
          element={
            <Protected>
              <Patients />
            </Protected>
          }
        />
        <Route
          path="/appointments"
          element={
            <Protected>
              <Appointments />
            </Protected>
          }
        />
        <Route
          path="/reviews"
          element={
            <Protected>
              <Reviews />
            </Protected>
          }
        />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </>
  );
}
