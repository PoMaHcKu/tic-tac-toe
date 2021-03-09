import React, {useContext} from 'react'
import {Context} from "../reducers/store";

function ErrorComponent({message}) {

    const [state, dispatch] = useContext(Context)

    function clearError() {
        dispatch({type: "CLEAR_ERROR"})
    }

    return (
        <div>
            <div onClick={clearError}>CLOSE</div>
            <h3>{message}</h3>
        </div>
    )
}

export default ErrorComponent