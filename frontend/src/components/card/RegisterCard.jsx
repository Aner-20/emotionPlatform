import { useEffect, useState, useContext } from "react";
import "./RegisterCard.css"

import AuthContext from "../../context/AuthContext";

import Modal from "../modal/Modal";


function RegisterCard(){

    const [departments, setDepartments] = useState([])
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [departmentId, setDepartmentId] = useState("");

    const [showModal, setShowModal] = useState(false);
    


    const isPasswordValid =  password.length >= 8 &&
    /[A-Z]/.test(password) &&
    /[a-z]/.test(password) &&
    /[0-9]/.test(password);
        
    const isFirstNameValid = firstName.length >= 3;
    const isLastNameValid = lastName.length >= 3;
    const isValidEmail = (email) => {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    };

    const { register } = useContext(AuthContext);

    useEffect(() => {
        const fetchDepartments = async () => {
            const response = await fetch( "http://localhost:8080/api/departments/registration")
            
            if (!response.ok){
                 throw new Error("Errore nel recupero dei dipartimenti");
            }
        
            const data = await response.json();
            
            setDepartments(data);

            // Fa in modo che venga selezionato in automatico il primo dipartimento della tabella departments
            if (data.length > 0){
                setDepartmentId(data[0].id);
            }

        };

        fetchDepartments();

    }, []) // [] useEffect viene eseguito quando RegisterCard viene montata


    const registerUser =  async () => {
        if (isFirstNameValid && isLastNameValid && isPasswordValid && isValidEmail(email)){
            try {
                await register(firstName, lastName, email, password, departmentId);
                setShowModal(true);
                setFirstName("");
                setLastName("");
                setPassword("");
                setEmail("");
                setDepartmentId("");

            } catch (error) {
                console.error("Errore durante la registrazione:", error);
            }
        }
        else {
            console.log("Not ok")
        }
    }
    
    // key={department.id} serve a react per identificare in modo univoco ogni elemento della lista
    // value={department.id} il valore associato a questo elemento HTML deve essere department.id
    // && se la condizione è vera mostra l'elemento. condizione && elemento
    return (
        <>
            <div className="register-card">
                <div className="data-user-section">
                    <input type="text" placeholder="Nome" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                    {firstName.length >= 0 && (
                        <p>
                            {isFirstNameValid ? "✓ Nome valido" :  "✗ Il nome deve avere almeno 3 caratteri"}
                        </p>
                    )}
                    <input type="text" placeholder="Cognome" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                    {lastName.length >= 0 && (
                        <p>
                            {isLastNameValid ? "✓ Cognome valido" :  "✗ Il cognome deve avere almeno 3 caratteri"}
                        </p>
                    )}
                    <div className="password-field">
                            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)}/>
                            {password.length >= 0 && (
                                <div className="password-requirements">
                                    <p>{password.length >= 8 ? "✓" : "✗"} Almeno 8 caratteri</p>
                                    <p>{/[A-Z]/.test(password) ? "✓" : "✗"} Una lettera maiuscola</p>
                                    <p>{/[a-z]/.test(password) ? "✓" : "✗"} Una lettera minuscola</p>
                                    <p>{/[0-9]/.test(password) ? "✓" : "✗"} Un numero</p>
                                </div>
                            )}
                    </div>
                    
                    <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                    {email.length >= 0 && (
                        isValidEmail(email) ? <p>Email valida</p> : <p>Email non valida</p>
                    )}
                    <select value={departmentId} onChange={(e) => setDepartmentId(Number(e.target.value))}>
                        {departments.map((department) => (
                            <option key={department.id} value={department.id}>{department.name}</option>
                        ))}
                    </select>
                    
                    
                </div>
                <div className="button-section">
                    <button className="registration-button" onClick={registerUser} 
                    disabled={
                         !isFirstNameValid ||
                         !isLastNameValid ||
                         !isPasswordValid ||
                         !isValidEmail(email)
                    }>Registrati</button>
                </div>
            </div>
        
        {showModal && (
            
            <Modal
                    title="Modulo di registrazione"
                    message="Registrazione effettuata con successo!"
                    onClose={() => setShowModal(false)}
                />  
                
            )}

       </> 
    )
}

export default RegisterCard
