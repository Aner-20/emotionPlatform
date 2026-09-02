
import "./AccountModal.css"

function AccountModal({ user, onClose}){
    return (
        <div className="modal-overlay">
            <div className="account-modal">
                <h2>Il mio account</h2>
                <p>Nome: {user.firstName}</p>
                <p>Cognome: {user.lastName}</p>
                <p>Email: {user.email}</p>
                <p>Dipartimento: {user.department.name}</p>

                <button onClick={onClose}>Chiudi</button>
            </div>
        </div>
    )
}

export default AccountModal;