import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Auth() {
  const [email, setEmail] = useState("");
  const [pwd, setPwd] = useState("");
  const [msg, setMsg] = useState("");
  const nav = useNavigate();

  const submit = async (e) => {
    e.preventDefault();
    setMsg("");

    try {
      const res = await fetch("http://localhost:8088/admin-service/api/admin/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ emailId: email, password: pwd })
      });

      if (!res.ok) {
        // Try to surface the backend’s error body (text or JSON)
        let serverMsg = "";
        const contentType = res.headers.get("content-type") || "";
        try {
          serverMsg = contentType.includes("application/json")
            ? (await res.json())?.message || JSON.stringify(await res.json())
            : await res.text();
        } catch {
          /* ignore parse errors */
        }
        setMsg(serverMsg || `Login failed (HTTP ${res.status})`);
        return;
      }

      const data = await res.json().catch(() => ({}));
      localStorage.setItem("auth", "1");
      localStorage.setItem("userEmail", email);
      if (data.token) localStorage.setItem("token", data.token);

      nav("/dashboard");
    } catch (err) {
      console.error("LOGIN NETWORK ERROR:", err);
      // CORS or network failures land here
      setMsg("Server not reachable (check Gateway/CORS/route). See console for details.");
    }
  };

  return (
    <div className="container" style={{ paddingTop: 24 }}>
      <form className="card" onSubmit={submit} style={{ maxWidth: 600 }}>
        <h3>Login</h3>
        <div className="row" style={{ marginBottom: 8 }}>
          <input
            placeholder="email@example.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="••••••••"
            value={pwd}
            onChange={(e) => setPwd(e.target.value)}
            required
          />
        </div>
        <button className="btn btn-primary" type="submit">Sign in</button>
        {msg && <div style={{ marginTop: 12, color: "red" }}>{msg}</div>}
      </form>
    </div>
  );
}
