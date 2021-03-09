import './App.css'
import * as React from 'react'
import {useRoutes} from "hookrouter";
import Nav from "./components/Nav";
import Games from "./components/Games";
import Login from "./components/auth/Login";
import RegistrationForm from "./components/auth/RegistrationForm";

const routes = {
    '/': () => <Games/>,
    '/login': () => <Login/>,
    '/registration': () => <RegistrationForm/>
}

function App() {

    const myRoutes = useRoutes(routes)

    return (
        <div className="container">
            <div className="header">
                <span className="header_link">CROSSES-ZEROS</span>
            </div>
            <div className="content">
                <Nav/>
                <div>
                    {myRoutes}
                </div>
            </div>
            <div className="footer">

            </div>
        </div>
    );
}

export default App;
