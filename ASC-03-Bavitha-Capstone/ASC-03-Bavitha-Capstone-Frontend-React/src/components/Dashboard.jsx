import { Link } from "react-router-dom";

export default function Dashboard() {
  const tiles = [
    { to: "/hospitals", label: "Hospitals" },
    { to: "/doctors", label: "Doctors" },
    { to: "/patients", label: "Patients" },
    { to: "/appointments", label: "Appointments" },
    { to: "/reviews", label: "Reviews" },
  ];
  return (
    <div className="container">
      <h2>Choose a module</h2>
      <div className="grid" style={{ marginTop: 16 }}>
        {tiles.map((t) => (
          <Link key={t.to} to={t.to} className="tile">
            {t.label}
          </Link>
        ))}
      </div>
    </div>
  );
}
