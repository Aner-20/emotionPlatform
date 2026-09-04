
import "./UserGreetingSection.css"

function UserGreetingSection({ user }){
    return (
        <section className="greeting-section">Ciao {user.firstName}</section>
    )
}

export default UserGreetingSection;