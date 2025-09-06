import { useEffect, useState } from "react";

const API_BASE = "http://localhost:8088";
const PATH = "/patient-service/api/patients";

export default function Patients() {
  const empty = { id:"", name:"", email:"", phone:"", gender:"", address:"", city:"", bloodGroup:"", doctorId:"" };
  const [rows, setRows] = useState([]);
  const [form, setForm] = useState(empty);

  const load = () => fetch(`${API_BASE}${PATH}`).then(r=>r.json()).then(setRows);
  useEffect(()=>{ load(); }, []);
  const onChange = (e)=> setForm({ ...form, [e.target.name]: e.target.value });

  const save = async (e)=>{
    e.preventDefault();
    const method = form.id ? "PUT" : "POST";
    const url = form.id ? `${API_BASE}${PATH}/${form.id}` : `${API_BASE}${PATH}`;
    await fetch(url, { method, headers:{ "Content-Type":"application/json" }, body: JSON.stringify(form) });
    setForm(empty); load();
  };

  const del = async (id)=>{ await fetch(`${API_BASE}${PATH}/${id}`, { method:"DELETE" }); load(); };

  return (
    <div className="container">
      <h2>Patients</h2>

      <form className="card" onSubmit={save} style={{ marginBottom:16 }}>
        <div className="row">
          <input name="name" placeholder="Name *" value={form.name} onChange={onChange} required />
          <input name="email" placeholder="Email" value={form.email} onChange={onChange} />
          <input name="phone" placeholder="Phone" value={form.phone} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <input name="gender" placeholder="Gender" value={form.gender} onChange={onChange} />
          <input name="bloodGroup" placeholder="Blood Group" value={form.bloodGroup} onChange={onChange} />
          <input name="doctorId" placeholder="Doctor ID *" value={form.doctorId} onChange={onChange} required />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <input name="address" placeholder="Address" value={form.address} onChange={onChange} />
          <input name="city" placeholder="City" value={form.city} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <button className="btn btn-primary" type="submit">{form.id ? "Update" : "Add"}</button>
          {form.id && <button className="btn" type="button" onClick={()=>setForm(empty)}>Cancel</button>}
        </div>
      </form>

      <div className="card">
        <table className="table" style={{ width:"100%" }}>
          <thead>
            <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Gender</th><th>BG</th><th>Doctor</th><th>Actions</th></tr>
          </thead>
          <tbody>
            {rows.map(r=>(
              <tr key={r.id}>
                <td>{r.id}</td><td>{r.name}</td><td>{r.email}</td><td>{r.phone}</td>
                <td>{r.gender}</td><td>{r.bloodGroup}</td><td>{r.doctorId}</td>
                <td className="row" style={{ justifyContent:"center" }}>
                  <button className="btn btn-success" onClick={()=>setForm(r)}>Edit</button>
                  <button className="btn btn-danger" onClick={()=>del(r.id)}>Delete</button>
                </td>
              </tr>
            ))}
            {rows.length===0 && <tr><td colSpan="8" className="muted">No patients</td></tr>}
          </tbody>
        </table>
      </div>
    </div>
  );
}
