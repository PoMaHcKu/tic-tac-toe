import React, {useContext} from 'react'
import {useForm} from "react-hook-form";
import {passwordRestriction, usernameRestriction} from "../../constants/formRestrictions";
import {registration} from "../../dao/loginRequest";
import ErrorComponent from "../ErrorComponent";
import {Context} from "../../reducers/store";

function RegistrationForm({error}) {

    const {register, handleSubmit, errors} = useForm()
    const [state, dispatch] = useContext(Context)
    const onSubmit = user => {
        if (checkPasswords(user)) {
            registration(user)
                .then(() => alert("success"))
                .catch(err => dispatch({type: "SET_ERROR", err: err.response.data.message}))
        }
    }

    const checkPasswords = (user) => {
        return user.password === user.password_repeat

    }
    return state.error ? <ErrorComponent message={state.error}/> :
        <form onSubmit={handleSubmit(onSubmit)}>
            <div>
                <input className="form_field" type="text" name="login"
                       ref={register(usernameRestriction)}
                       placeholder="username"/>
            </div>
            <div>
                <input className="form_field" type="password" name="password"
                       ref={register(passwordRestriction)}
                       placeholder="password"/>
            </div>
            <div>
                <input className="form_field" type="password" name="password_repeat"
                       ref={register}
                       placeholder="repeat password"/>
            </div>
            <div>
                <input className="form_field form_button" type="submit" value="Sign In"/>
            </div>
        </form>
}

export default RegistrationForm