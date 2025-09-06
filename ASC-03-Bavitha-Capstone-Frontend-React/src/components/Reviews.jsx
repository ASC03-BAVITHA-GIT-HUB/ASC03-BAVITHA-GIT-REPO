import { useEffect, useState } from "react";

const API_BASE = "http://localhost:8088";
const PATH = "/review-service/api/reviews";

export default function Reviews() {
  const empty = { id:"", hospitalId:"", comment:"", rating:5, createdAt:"" };
  const [rows, setRows] = useState([]);
  const [form, setForm] = useState(empty);

  const load = () => fetch(`${API_BASE}${PATH}`).then(r=>r.json()).then(setRows);
  useEffect(()=>{ load(); }, []);
  const onChange = (e)=> setForm({ ...form, [e.target.name]: e.target.value });

  const save = async (e)=>{
    e.preventDefault();
    const body = {
      hospitalId: form.hospitalId,
      comment: form.comment,
      rating: Number(form.rating) || 0,
      createdAt: form.createdAt || null
    };
    await fetch(`${API_BASE}${PATH}`, {
      method:"POST", headers:{ "Content-Type":"application/json" }, body: JSON.stringify(body)
    });
    setForm(empty); load();
  };

  const del = async (id)=>{ await fetch(`${API_BASE}${PATH}/${id}`, { method:"DELETE" }); load(); };

  return (
    <div className="container">
      <h2>Reviews</h2>

      <form className="card" onSubmit={save} style={{ marginBottom:16 }}>
        <div className="row">
          <input name="hospitalId" placeholder="Hospital ID *" value={form.hospitalId} onChange={onChange} required />
          <input name="rating" type="number" min="1" max="5" value={form.rating} onChange={onChange} />
          <input name="createdAt" type="datetime-local" value={form.createdAt} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <input name="comment" placeholder="Comment" value={form.comment} onChange={onChange} />
        </div>
        <div className="row" style={{ marginTop:8 }}>
          <button className="btn btn-primary" type="submit">Add Review</button>
        </div>
      </form>

      <div className="card">
        <table className="table" style={{ width:"100%" }}>
          <thead>
            <tr><th>ID</th><th>Hospital</th><th>Rating</th><th>Comment</th><th>Created</th><th>Actions</th></tr>
          </thead>
          <tbody>
            {rows.map(r=>(
              <tr key={r.id}>
                <td>{r.id}</td><td>{r.hospitalId}</td><td>{r.rating}</td>
                <td>{r.comment}</td><td>{r.createdAt}</td>
                <td className="row" style={{ justifyContent:"center" }}>
                  <button className="btn btn-danger" onClick={()=>del(r.id)}>Delete</button>
                </td>
              </tr>
            ))}
            {rows.length===0 && <tr><td colSpan="6" className="muted">No reviews</td></tr>}
          </tbody>
        </table>
      </div>
    </div>
  );
}
