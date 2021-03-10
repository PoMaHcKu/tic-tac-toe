import React, {useContext} from 'react'
import {useForm} from "react-hook-form";
import {login} from "../../dao/loginRequest";
import {passwordRestriction, usernameRestriction} from "../../constants/formRestrictions";
import {Context} from "../../reducers/store";
import {setUserAC} from "../../constants/actionCreators";

function LoginForm() {

    const {register, handleSubmit, formState, setError} = useForm({mode: "onChange"})
    const [state, dispatch] = useContext(Context)
    const onSubmit = data => login(data)
        .then(resp => dispatch(setUserAC({...resp})))
        .catch(err => {
            setError("login", {message: err.response.data.message})
            setError("password", {message: err.response.data.message})
        })

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="form_field_block">
                <label className="form_label" htmlFor="login">Login</label>
                <input className="form_field" type="text" name="login"
                       ref={register(usernameRestriction)}
                       placeholder="username"/>
                <div className="not_validate_field_form">
                    {formState.errors.login &&
                    <span>{formState.errors.login.message || "Must be more characters"}</span>}
                </div>
            </div>
            <div className="form_field_block">
                <label className="form_label" htmlFor="password">Password</label>
                <input className="form_field" type="password" name="password"
                       ref={register(passwordRestriction)}
                       placeholder="password"/>
                <div className="not_validate_field_form">
                    {formState.errors.login &&
                    <span>{formState.errors.login.message || "Password must be as least 8 chars"}</span>}
                </div>
            </div>
            <div>
                <input className="form_field form_button" type="submit" value="Sign In"/>
            </div>
        </form>
    );
}

export default LoginForm