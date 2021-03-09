import {
    CLEAR_ERROR,
    SET_ERROR, SIGN_OUT
} from "./actionTypes"


export function setErrorAC(message) {
    return {type: SET_ERROR, err: message}
}

export function clearErrorAC() {
    return {type: CLEAR_ERROR}
}

export function signOutAC() {
    return {type: SIGN_OUT}
}

export function setUserAC(user) {
    return {type: "LOGIN", user}
}

