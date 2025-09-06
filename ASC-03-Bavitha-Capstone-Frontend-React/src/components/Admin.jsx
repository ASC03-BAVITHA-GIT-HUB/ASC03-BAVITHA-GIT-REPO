import { useEffect, useState } from "react";

const API = "http://localhost:8088/admin_service/api/admin";

export default function Admin() {
  const empty = { emailId: "", password: "" };
  const [items, setItems] = useState([]);
  const [form, setForm] = useState(empty);

  const load = () => fetch(API).then(r => r.json()).then(setItems);

  useEffect(() => { load(); }, []);

  const onChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const onCreate = (e) => {
    e.preventDefault();
    fetch(`${API}/auth/register`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form)
    })
      .then(r => r.json())
      .then(() => { setForm(empty); load(); });
  };

  const onDelete = (id) =>
    fetch(`${API}/${id}`, { method: "DELETE" }).then(() => load());

  return (
    <div className="card">
      <h2>Admins</h2>

      <form className="form" onSubmit={onCreate}>
        <input name="emailId" placeholder="emailId" value={form.emailId} onChange={onChange} required />
        <input name="password" type="password" placeholder="password" value={form.password} onChange={onChange} required />
        <button type="submit">Add</button>
      </form>

      <table className="table">
        <thead><tr><th>ID</th><th>Email</th><th>Actions</th></tr></thead>
        <tbody>
          {items.map(a => (
            <tr key={a.id}>
              <td>{a.id}</td>
              <td>{a.emailId}</td>
              <td><button onClick={() => onDelete(a.id)}>Delete</button></td>
            </tr>
          ))}
          {items.length === 0 && <tr><td colSpan="3">No admins</td></tr>}
        </tbody>
      </table>
    </div>
  );
}
