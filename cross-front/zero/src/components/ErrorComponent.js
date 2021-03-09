import React, {useContext} from 'react'
import {Context} from "../reducers/store";
import {clearErrorAC} from "../constants/actionCreators";

function ErrorComponent({message}) {

    const [state, dispatch] = useContext(Context)

    function clearError() {
        dispatch(clearErrorAC())
    }

    return (
        <div>
            <div onClick={clearError}>CLOSE</div>
            <h3>{message}</h3>
        </div>
    )
}

export default ErrorComponent