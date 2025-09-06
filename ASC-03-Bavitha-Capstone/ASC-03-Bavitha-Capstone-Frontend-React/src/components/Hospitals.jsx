import { useEffect, useState } from "react";

const API_BASE = "http://localhost:8088";
const PATH = "/hospital-service/api/hospitals";

export default function Hospitals() {
  const empty = { id: "", name: "", email: "", phone: "", address: "", city: "" };
  const [rows, setRows] = useState([]);
  const [form, setForm] = useState(empty);
  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState("");

  const load = async () => {
    setLoading(true);
    try {
      const res = await fetch(`${API_BASE}${PATH}`);
      const data = await res.json();
      setRows(data);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load(); }, []);
  const onChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const onSubmit = async (e) => {
    e.preventDefault();
    setMsg("");
    const method = form.id ? "PUT" : "POST";
    const url = form.id ? `${API_BASE}${PATH}/${form.id}` : `${API_BASE}${PATH}`;
    const res = await fetch(url, { method, headers:{ "Content-Type":"application/json" }, body: JSON.stringify(form) });
    if (!res.ok) { setMsg("Save failed"); return; }
    setForm(empty);
    await load();
    setMsg("Saved!");
  };

  const onEdit = (r) => setForm({ ...r });
  const onCancel = () => setForm(empty);
  const onDelete = async (id) => {
    if (!confirm(`Delete hospital ${id}?`)) return;
    await fetch(`${API_BASE}${PATH}/${id}`, { method: "DELETE" });
    load();
  };

  return (
    <div className="container">
      <h2>Hospitals</h2>

      <form className="card" onSubmit={onSubmit} style={{ marginBottom: 16 }}>
        <div className="row">
          <input name="name" placeholder="Name *" value={form.name} onChange={onChange} required />
          <input name="email" placeholder="Email" value={form.email} onChange={onChange} />
          <input name="phone" placeholder="Phone" value={form.phone} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop: 8 }}>
          <input name="address" placeholder="Address" value={form.address} onChange={onChange} />
          <input name="city" placeholder="City" value={form.city} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop: 8 }}>
          <button className="btn btn-primary" type="submit">{form.id ? "Update" : "Add"}</button>
          {form.id && <button type="button" className="btn" onClick={onCancel}>Cancel</button>}
        </div>
        {msg && <div className="small muted" style={{ marginTop: 6 }}>{msg}</div>}
      </form>

      <div className="card">
        {loading ? (
          <div className="muted">Loading…</div>
        ) : (
          <table className="table" style={{ width: "100%" }}>
            <thead>
              <tr>
                <th>ID</th><th>Name</th><th>Email</th><th>Phone</th>
                <th>Address</th><th>City</th><th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {rows.length === 0 ? (
                <tr><td colSpan="7" className="muted">No hospitals found</td></tr>
              ) : rows.map(r => (
                <tr key={r.id}>
                  <td>{r.id}</td><td>{r.name}</td><td>{r.email}</td>
                  <td>{r.phone}</td><td>{r.address}</td><td>{r.city}</td>
                  <td className="row" style={{ justifyContent: "center" }}>
                    <button className="btn btn-success" onClick={() => onEdit(r)}>Edit</button>
                    <button className="btn btn-danger" onClick={() => onDelete(r.id)}>Delete</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
}
