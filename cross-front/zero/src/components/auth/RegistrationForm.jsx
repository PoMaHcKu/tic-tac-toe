import React, {useContext, useRef} from 'react'
import {useForm} from "react-hook-form";
import {passwordRestriction, usernameRestriction} from "../../constants/formRestrictions";
import {registration} from "../../dao/loginRequest";
import ErrorComponent from "../ErrorComponent";
import {Context} from "../../reducers/store";

function RegistrationForm() {

    const {register, handleSubmit, watch, formState, setError} = useForm(
        {
            mode: 'onChange'
        })
    const [state] = useContext(Context)
    const password = useRef({});
    password.current = watch("password", "");
    const onSubmit = user => {
        registration(user)
            .then(() => alert("success"))
            .catch(err => setError("login", {message: err.response.data.message}))
    }

    return state.error ? <ErrorComponent message={state.error}/> :
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
                    {formState.errors.password &&
                    <span>Password must be as least 8 chars</span>}
                </div>
            </div>
            <div className="form_field_block">
                <label className="form_label" htmlFor="password_repeat">Password repeat</label>
                <input className="form_field" type="password" name="password_repeat"
                       ref={register({
                           validate: value => password.current === value || "Not match"
                       })}
                       placeholder="repeat password"/>
                <div className="not_validate_field_form">
                    {formState.errors.password_repeat &&
                    <span>{formState.errors.password_repeat.message}</span>}
                </div>
            </div>
            <div>
                <input className="form_field form_button"
                       disabled={!formState.isValid}
                       type="submit"
                       value="Sign Up"/>
            </div>
        </form>
}

export default RegistrationForm