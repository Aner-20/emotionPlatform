import { useEffect, useState } from "react";
import "./RegisterCard.css"

function RegisterCard(){

    const [departments, setDepartments] = useState([])
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [departmentId, setDepartmentId] = useState("");

    const isPasswordValid = password.length >= 8;
    const isFirstNameValid = firstName.length >= 3;
    const isLastNameValid = lastName.length >= 3;

    useEffect(() => {
        const fetchDepartments = async () => {
            const response = await fetch( "http://localhost:8080/api/departments/registration")
            
            if (!response.ok){
                 throw new Error("Errore nel recupero dei dipartimenti");
            }
        
            const data = await response.json();
            
            setDepartments(data);

        };

        fetchDepartments();

    }, []) // [] useEffect viene eseguito quando RegisterCard viene montata

    console.log(departments)
    
    // key={department.id} serve a react per identificare in modo univoco ogni elemento della lista
    // value={department.id} il valore associato a questo elemento HTML deve essere department.id

    return (
        <div className="register-card">
            <div className="data-user-section">
                <input type="text" placeholder="Nome" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                {firstName.length >= 0 && (
                    <p>
                        {isFirstNameValid ? "✓ Nome valido" :  "✗ Il nome deve avere almeno 3 caratteri"}
                    </p>
                )}
                <input type="text" placeholder="Cognome" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                <p>Nome valido</p>
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}/>
                 <p>Nome valido</p>
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                 <p>Nome valido</p>
                <select value={departmentId} onChange={(e) => setDepartmentId(e.target.value)}>
                    {departments.map((department) => (
                        <option key={department.id} value={department.id}>{department.name}</option>
                    ))}
                </select>
                
                
            </div>
            <div className="button-section">
                <button>Registrati</button>
            </div>
        </div>
    )
}

export default RegisterCard
