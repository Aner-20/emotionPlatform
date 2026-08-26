import "./Modal.css"

function Modal({ title, message, onClose}){
    return (
        <div className="modal-overlay">
            <div className="modal">
                <h2>{title}</h2>
                <p>{message}</p>
                <button onClick={onClose}>Chiudi</button>
            </div>
        </div>
    )
}

export default Modal;
