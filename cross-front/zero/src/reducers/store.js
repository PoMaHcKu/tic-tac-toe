import GlobalReducer from "./globalReducer"
import {createContext, useReducer} from "react"

const initialState = {
    games: [],
    user: {},
    error: null
}

const Store = ({children}) => {
    const [state, dispatch] = useReducer(GlobalReducer, initialState)

    return (
        <Context.Provider value={[state, dispatch]}>
            {children}
        </Context.Provider>
    )
}

export const Context = createContext(initialState)
export default Store