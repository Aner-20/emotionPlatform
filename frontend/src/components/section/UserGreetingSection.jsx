
import "./UserGreetingSection.css"

function UserGreetingSection({ user }){
    return (
        <section className="greeting-section">
            <div className="greeting-container">
                Ciao {user.firstName}
            </div>
           
        </section>
    )
}

export default UserGreetingSection;