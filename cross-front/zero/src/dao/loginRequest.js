import request from "./request";

export function login(credentials) {
    return request.post('login', credentials)
        .then(response => response.data)
}

export function registration(user) {
    return request.post('register', user)
        .then(response => response.data)
}