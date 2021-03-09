import React, {useContext} from 'react'
import {useForm} from "react-hook-form";
import {login} from "../../dao/loginRequest";
import {passwordRestriction, usernameRestriction} from "../../constants/formRestrictions";
import {Context} from "../../reducers/store";
import {Redirect} from "react-router";

function LoginForm() {

    const {register, handleSubmit, errors} = useForm()
    const [state, dispatch] = useContext(Context)
    const onSubmit = data => login(data)
        .then(resp => {
            let user = {...resp}
            dispatch({type: "LOGIN", user: user})
        }).catch(err => console.log(JSON.parse(err)))

    if (state.user.token) {
        return <Redirect to="/"/>
    }

    return (
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
            {errors.exampleRequired && <span>This field is required</span>}
            <div>
                <input className="form_field form_button" type="submit" value="Sign In"/>
            </div>
        </form>
    );
}

export default LoginForm