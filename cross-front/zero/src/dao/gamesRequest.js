import request from "./request";

export function getGames() {
    return request.get('game')
        .then(request => request.data)
        .catch(err => console.log(err.message))
}