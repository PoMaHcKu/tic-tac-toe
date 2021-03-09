const GlobalReducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            return {
                ...state,
                user: {
                    ...action.user
                }
            }
        case "SIGN_OUT":
            return {
                ...state,
                user: {}
            }
        case "GET_GAMES":
            return {
                ...state,
                games: [
                    ...action.payload
                ]
            }
        case "REPLACE_GAME":
            return {
                ...state,
                games: [...this.games.map(game => {
                    if (game.id === action.payload.id) {
                        return action.payload
                    }
                    return game
                })]
            }
        case "SET_ERROR":
            return {
                ...state,
                error: action.err
            }
        case "CLEAR_ERROR":
            return {
                ...state,
                error: ''
            }
        default:
            return state
    }
}

export default GlobalReducer