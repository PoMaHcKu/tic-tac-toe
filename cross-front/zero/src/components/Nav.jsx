import {A} from "hookrouter";
import * as React from "react";
import {useContext} from "react";
import {Context} from "../reducers/store";
import {signOutAC} from "../constants/actionCreators";

function Nav() {

    const [state, setState] = useContext(Context)

    console.log(state.user)

    return (
        <div className="nav">
            <A className="nav_item" href="/">GAMES</A>
            <br/>
            {!state.user.token ?
                <p>
                    <A className="nav_item" href="/login">LOGIN</A>
                    <br/>
                    <A className="nav_item" href="/registration">REGISTRATION</A>
                </p> :
                <A className="nav_item" href="/">
                    <div onClick={() => setState(signOutAC())}>SIGN OUT</div>
                </A>
            }
        </div>
    )
}

export default Nav