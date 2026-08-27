
import "./RegisterCard.css"



function RegisterCard(){
    return (
        <div className="register-card">
            <div className="data-user-section">
                <input type="text" placeholder="Nome" />
                <input type="text" placeholder="Cognome" />
                <input type="password" placeholder="Password"/>
                <input type="email" placeholder="Email" />
                <select>
                    <option value="IT">IT</option>
                    <option value="HR">HR</option>
                </select>
                
                
            </div>
            <div className="button-section">
                <button>Registrati</button>
            </div>
        </div>
    )
}

export default RegisterCard
