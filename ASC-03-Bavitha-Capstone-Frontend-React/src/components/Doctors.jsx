import { useEffect, useState } from "react";

const API_BASE = "http://localhost:8088";
const PATH = "/doctor-service/api/doctors";

export default function Doctors() {
  const empty = { id:"", name:"", email:"", phone:"", specialization:"", hospitalId:"" };
  const [rows, setRows] = useState([]);
  const [form, setForm] = useState(empty);

  const load = () => fetch(`${API_BASE}${PATH}`).then(r=>r.json()).then(setRows);
  useEffect(()=>{ load(); }, []);
  const onChange = (e)=> setForm({ ...form, [e.target.name]: e.target.value });

  const save = async (e) => {
    e.preventDefault();
    const method = form.id ? "PUT" : "POST";
    const url = form.id ? `${API_BASE}${PATH}/${form.id}` : `${API_BASE}${PATH}`;
    await fetch(url, { method, headers:{ "Content-Type":"application/json" }, body: JSON.stringify(form) });
    setForm(empty); load();
  };

  const del = async (id) => { await fetch(`${API_BASE}${PATH}/${id}`, { method:"DELETE" }); load(); };

  return (
    <div className="container">
      <h2>Doctors</h2>

      <form className="card" onSubmit={save} style={{ marginBottom:16 }}>
        <div className="row">
          <input name="name" placeholder="Name *" value={form.name} onChange={onChange} required />
          <input name="email" placeholder="Email" value={form.email} onChange={onChange} />
          <input name="phone" placeholder="Phone" value={form.phone} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <input name="specialization" placeholder="Specialization" value={form.specialization} onChange={onChange} />
          <input name="hospitalId" placeholder="Hospital ID *" value={form.hospitalId} onChange={onChange} required />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <button className="btn btn-primary" type="submit">{form.id ? "Update" : "Add"}</button>
          {form.id && <button className="btn" type="button" onClick={()=>setForm(empty)}>Cancel</button>}
        </div>
      </form>

      <div className="card">
        <table className="table" style={{ width:"100%" }}>
          <thead>
            <tr><th>ID</th><th>Name</th><th>Email</th><th>Spec</th><th>Hospital</th><th>Actions</th></tr>
          </thead>
          <tbody>
            {rows.map(r => (
              <tr key={r.id}>
                <td>{r.id}</td><td>{r.name}</td><td>{r.email}</td>
                <td>{r.specialization}</td><td>{r.hospitalId}</td>
                <td className="row" style={{ justifyContent:"center" }}>
                  <button className="btn btn-success" onClick={()=>setForm(r)}>Edit</button>
                  <button className="btn btn-danger" onClick={()=>del(r.id)}>Delete</button>
                </td>
              </tr>
            ))}
            {rows.length===0 && <tr><td colSpan="6" className="muted">No doctors</td></tr>}
          </tbody>
        </table>
      </div>
    </div>
  );
}
