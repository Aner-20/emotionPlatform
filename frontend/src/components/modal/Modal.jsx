import "./Modal.css"

import Button from "../button/Button";

function Modal({ title, message, onClose}){
    return (
        <div className="modal-overlay">
            <div className="modal">
                <h2>{title}</h2>
                <p>{message}</p>
                <Button text="Chiudi" onClick={onClose}></Button>
            </div>
        </div>
    )
}

export default Modal;
