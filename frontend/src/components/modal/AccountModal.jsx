
import "./AccountModal.css"

function AccountModal({ user, onClose}){
    return (
        <div className="modal-overlay">
            <div className="account-modal">
                <div className="account-info">
                    <h2>Il mio account</h2>
                    <p>Nome: {user.firstName}</p>
                    <p>Cognome: {user.lastName}</p>
                    <p>Email: {user.email}</p>
                    <p>Dipartimento: {user.department.name}</p>
                </div>
             

                <button onClick={onClose}>Chiudi</button>
            </div>
        </div>
    )
}

export default AccountModal;