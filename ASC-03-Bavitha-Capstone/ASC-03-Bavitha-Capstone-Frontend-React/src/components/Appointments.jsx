import { useEffect, useState } from "react";

const API_BASE = "http://localhost:8088";
const PATH = "/appointment-service/api/appointments";

export default function Appointments() {
  const empty = { id:"", patientId:"", doctorId:"", whenAt:"", reason:"", status:"SCHEDULED" };
  const [rows, setRows] = useState([]);
  const [form, setForm] = useState(empty);

  const load = () => fetch(`${API_BASE}${PATH}`).then(r=>r.json()).then(setRows);
  useEffect(()=>{ load(); }, []);
  const onChange = (e)=> setForm({ ...form, [e.target.name]: e.target.value });

  const save = async (e)=>{
    e.preventDefault();
    const body = { ...form, whenAt: form.whenAt ? new Date(form.whenAt).toISOString() : null };
    const method = form.id ? "PUT" : "POST";
    const url = form.id ? `${API_BASE}${PATH}/${form.id}` : `${API_BASE}${PATH}`;
    await fetch(url, { method, headers:{ "Content-Type":"application/json" }, body: JSON.stringify(body) });
    setForm(empty); load();
  };

  const del = async (id)=>{ await fetch(`${API_BASE}${PATH}/${id}`, { method:"DELETE" }); load(); };

  return (
    <div className="container">
      <h2>Appointments</h2>

      <form className="card" onSubmit={save} style={{ marginBottom:16 }}>
        <div className="row">
          <input name="patientId" placeholder="Patient ID *" value={form.patientId} onChange={onChange} required />
          <input name="doctorId" placeholder="Doctor ID" value={form.doctorId} onChange={onChange} />
          <input type="datetime-local" name="whenAt" value={form.whenAt} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <input name="reason" placeholder="Reason" value={form.reason} onChange={onChange} />
          <input name="status" placeholder="Status" value={form.status} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <button className="btn btn-primary" type="submit">{form.id ? "Update" : "Add"}</button>
          {form.id && <button className="btn" type="button" onClick={()=>setForm(empty)}>Cancel</button>}
        </div>
      </form>

      <div className="card">
        <table className="table" style={{ width:"100%" }}>
          <thead>
            <tr><th>ID</th><th>Patient</th><th>Doctor</th><th>When</th><th>Status</th><th>Reason</th><th>Actions</th></tr>
          </thead>
          <tbody>
            {rows.map(r=>(
              <tr key={r.id}>
                <td>{r.id}</td><td>{r.patientId}</td><td>{r.doctorId}</td>
                <td>{r.whenAt}</td><td>{r.status}</td><td>{r.reason}</td>
                <td className="row" style={{ justifyContent:"center" }}>
                  <button className="btn btn-success" onClick={()=>setForm({ ...r, whenAt: r.whenAt ? r.whenAt.slice(0,16) : "" })}>Edit</button>
                  <button className="btn btn-danger" onClick={()=>del(r.id)}>Delete</button>
                </td>
              </tr>
            ))}
            {rows.length===0 && <tr><td colSpan="7" className="muted">No appointments</td></tr>}
          </tbody>
        </table>
      </div>
    </div>
  );
}
